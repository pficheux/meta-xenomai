DESCRIPTION = "Linux/Xenomai kernel for Pi 3"
SECTION = "kernel"
LICENCE = "GPLv2"

require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

LINUX_VERSION ?= "4.9.45"

LINUX_VERSION_EXTENSION = "-xeno"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRCREV = "3ce72830a8c8bba33c37ebe4bee71ac3177451b0"

SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y"
SRC_URI += "file://ipipe-core-4.9.45-arm-custom.patch;apply=0"
SRC_URI += "file://defconfig"

# Xenomai source (prepare_kernel.sh script)
SRC_URI += "http://xenomai.org/downloads/xenomai/stable/xenomai-3.0.5.tar.bz2;name=xeno"
SRC_URI[xeno.md5sum] = "c309036358fd403e9894fbbf53be91a1"

do_prepare_kernel () {
    # Set linux kernel source directory
    linux_src="${S}"

    # Set xenomai source directory 
    xenomai_src="${WORKDIR}/xenomai-3.0.5"

    # Set ipipe patch (adapted for Pi 3)
    ipipe_patch="${WORKDIR}/ipipe-core-4.9.45-arm-custom.patch"

    # Prepare kernel
    ${xenomai_src}/scripts/prepare-kernel.sh --arch=arm --linux=${linux_src} --ipipe=${ipipe_patch} --default
}

addtask prepare_kernel after do_patch before do_configure

COMPATIBLE_MACHINE = "raspberrypi3"