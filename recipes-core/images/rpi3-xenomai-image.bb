# Basic image for Xenomai
include recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += "rt-tests xenomai xenomai-demos"

