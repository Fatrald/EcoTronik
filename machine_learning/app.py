import os
import gradio as gr
import tensorflow as tf
from PIL import Image
import requests
from io import BytesIO

labels = {
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
    predicted_class = labels[class_index]
    return predicted_class

def classify_image(image):
    predicted_class = predict_class(image)
    return predicted_class

inputs = gr.Image(type="pil", label="Upload an image")
outputs = gr.Label()

title = "Ecotronik"
description = "Upload an image and get the predicted class."

gr.Interface(fn=classify_image,
             inputs=inputs,
             outputs=outputs,
             title=title,
             description=description).launch()
