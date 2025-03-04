DESCRIPTION = "Linux kernel with Dovetail support (BBB)"
SECTION = "kernel"
LICENCE = "GPLv2"

require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.10.199"

PV = "${LINUX_VERSION}+git${SRCPV}"

# Use Xenomai 3.2.4
XENO_V = "v3.2.4"

SRCREV = "5b170c53ab35809dc3054114d7423a8b7a7a89ad"
SRCREV_meta = "d787ac6931e013c185858482f491f8bfa9fa5177"

SRC_URI = "git://source.denx.de/Xenomai/linux-dovetail.git;protocol=https;branch=v5.10.y-dovetail file://defconfig file://fragment.cfg"
SRC_URI += "git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-5.10;destsuffix=${KMETA};protocol=https"
KMETA = "kernel-meta"

COMPATIBLE_MACHINE = "beaglebone-yocto"

SRC_URI += "https://source.denx.de/Xenomai/xenomai/-/archive/${XENO_V}/xenomai-${XENO_V}.tar.bz2"

SRC_URI[md5sum] = "8a1be4adb61a937d80360675187879a9"

do_xenomai_prepare_kernel () {
  ${WORKDIR}/xenomai-${XENO_V}/scripts/prepare-kernel.sh --arch=arm --linux=${S}
}

addtask xenomai_prepare_kernel after do_patch before do_configure
