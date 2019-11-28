import cv2
import sys
sys.path

from joblib import load
from sklearn.utils import shuffle
from skimage.feature import hog

classifier = load(".\\src\\main\\java\\com\\tcc\\recognize\\scripts\\model.joblib")

x_val = []
results = []
data = sys.argv[1:]

for f in data:
    
    try:
        img = cv2.imread(f, cv2.COLOR_RGB2GRAY)
        img = cv2.resize(img, (256, 256))

        if img is not None:
            features = hog(img, orientations=9, pixels_per_cell=(16, 16), cells_per_block=(1, 1), block_norm='L2', visualize=False, transform_sqrt=False, feature_vector=True, multichannel=None)
            x_val.append(features)
            
    except cv2.error as e:
        print('Error: ', e)

for i, j in zip(classifier.predict(x_val), data):
    results.append([i,j])

print(results)