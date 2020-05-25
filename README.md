# CharacterClassifier

The core of this Character Classifier app is built using Tensorflow and a deep Convolutional neural network. The neural network was trained on EMNIST dataset AKA Extended MNIST dataset and the model achieved an accuracy of 92% on the test set.

The EMNIST dataset is a set of handwritten character digits derived from the NIST Special Database 19 and converted to a 28x28 pixel image format and dataset structure that directly matches the MNIST dataset. Further information on the dataset contents and conversion process can be found in the paper available at https://arxiv.org/abs/1702.05373v1.

# Balanced dataset
To build this model I used the Balanced version of the EMNIST dataset.
The EMNIST Balanced dataset is meant to address the balance issues in the ByClass and ByMerge datasets. It is derived from the ByMerge dataset to reduce mis-classification errors due to capital and lower case letters and also has an equal number of samples per class. This dataset is meant to be the most applicable.

train: 112,800
test: 18,800
total: 131,600
classes: 47 (balanced)

# Android App
The app is written totally in Kotlin and also uses Tensorflow Lite library for android. Link: https://play.google.com/store/apps/details?id=com.shway.character

# Screenshots
