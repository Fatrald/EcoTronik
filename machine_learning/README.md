# Machine Learning Path
As machine learning engineers, our primary role was on developing a model using Keras within the TensorFlow framework. The purpose of this model was to accurately classify images of type of electronic waste that would be captured by EcoTronik.

## Machine Learning Jobdesc
* Collecting the dataset from both https://images.cv/ and scraping techniques.
* Pre-processing the images.
* Create and Training model using MobileNetv2 and some additional layers.
* Saved the best model (h5).
* Predict some images with the best model.
* Deploy ML Gradio App on Hugging Face Spaces.
* Create a dockerfile for deployment.

## Collecting the dataset
For building EcoTronik prototype, we collected around 7200 for 12 classes type of electronic waste, which are:
1. Battery
2. Lamp
3. Keyboard
4. Mouse
5. Smartphone
6. Fan
7. Cable
8. Laptop
9. Headphones
10. Monitor
11. Speaker
12. Television

The used dataset were split into 3 sets, that are training, validation, and testing set. The training set contains 538 images of each e-waste's class, validation set contains 30 images of each e-waste's class, and testing set contains 30 images of each e-waste's class. We collected dataset from https://images.cv/ and scraping techniques.

## The General Information of The Pre-trained Model Using MobileNetv2
MobileNetV2 is a convolutional neural network (CNN) architecture that was introduced as an extension of the original MobileNet model. We use MobileNetV2 because it achieves a good balance between model size and accuracy, allowing for faster inference and lower memory usage compared to larger and more complex models. 

## The Type of Electronic Waste Model Notebook
We utilized the MobileNetV2 pre-trained model to extract some feautures as it's mentioned above, then fine-tuned the model with adding the CNN layers above and below it. The testing phase resulted in an accuracy of 89.72% for the trained model. Please refer to <a href="https://github.com/Fatrald/EcoTronik/blob/main/machine_learning/EcoTronik_final.ipynb">this</a> link to view the complete steps and result.

## The Machine Learning Model App
We utilized the Hugging Face Platform to develop our machine learning model app. Please refer to <a href="https://huggingface.co/spaces/baghas26/ecotronik-new">this</a> link if you want to view and try the result of Gradio App.
