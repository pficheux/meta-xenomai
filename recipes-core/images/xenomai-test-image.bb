# Basic image for Xenomai
include recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += "xenomai"

# Add demos
IMAGE_INSTALL += "xenomai-demos"

