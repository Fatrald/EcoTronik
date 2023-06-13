import os
import traceback
import tensorflow as tf
from PIL import Image
from io import BytesIO
from flask import Flask, request, jsonify
import requests

app = Flask(__name__)

scores = {
    0: "battery",
    1: "cable",
    2: "fan",
    3: "headphones",
    4: "keyboard",
    5: "lamp",
    6: "laptop",
    7: "monitor",
    8: "mouse",
    9: "smartphone",
    10: "speaker",
    11: "television"
}

classes = {
    "battery": 1,
    "keyboard": 2,
    "laptop": 3,
    "smartphone": 4,
    "cable": 6,
    "television": 7,
    "headphones": 8,
    "speaker": 9,
    "monitor": 10,
    "fan": 11,
    "lamp": 12,
    "mouse": 13
}

model_url = "https://storage.googleapis.com/ecotronik-model/ecotronik.h5"
model_file = "ecotronik.h5"

# Check if the model file exists locally
if not os.path.exists(model_file):
    # Download the model file
    response = requests.get(model_url)
    response.raise_for_status()
    with open(model_file, "wb") as f:
        f.write(response.content)

# Load the model
model = tf.keras.models.load_model(model_file)

def predict_class(image):
    img = tf.cast(image, tf.float32)
    img = tf.image.resize(img, [224, 224])
    img = tf.expand_dims(img, axis=0)
    prediction = model.predict(img)
    class_index = tf.argmax(prediction[0]).numpy()
    return class_index

def classify_image(image):
    class_index = predict_class(image)
    class_number = classes[scores[class_index]]
    return class_number

@app.route("/predict_image", methods=["POST"])
def predict_image():
    try:
        if "image" not in request.files:
            return jsonify({"error": "No image uploaded."}), 400

        image_file = request.files["image"]
        image = Image.open(image_file)
        class_number = classify_image(image)

        return jsonify({"class_number": class_number})
    except Exception as e:
        traceback.print_exc()
        return jsonify({"error": "Internal Server Error"}), 500

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=7860)
