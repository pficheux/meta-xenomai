
PACKAGE_INSTALL_append = " kernel-modules"

ROOTFS_POSTPROCESS_COMMAND_append = " clobber_unused;"

clobber_unused () {
        rm ${IMAGE_ROOTFS}/boot/*
}

INITRAMFS_MAXSIZE = "195219"
