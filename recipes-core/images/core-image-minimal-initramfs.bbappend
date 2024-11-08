
PACKAGE_INSTALL:append = " kernel-modules"

ROOTFS_POSTPROCESS_COMMAND:append = " clobber_unused;"

clobber_unused () {
        rm ${IMAGE_ROOTFS}/boot/*
}

INITRAMFS_MAXSIZE = "195219"
