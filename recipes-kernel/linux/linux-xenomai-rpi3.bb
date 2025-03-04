DESCRIPTION = "Linux kernel with Xenomai patch for Raspberry Pi 3"
SECTION = "kernel"
LICENCE = "GPLv2"

require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION ?= "4.19.128"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRCREV = "c687d5c9b677c4c09e2391afc4921c0430bec4be"

# default is Xenomai 3.2.1
XENO_V = "v3.2.1"
SRC_URI[md5sum] = "ce1bf6f6ec5c01dc17e184003f740321"

IPIPE_PATCH = "ipipe-core-4.19.128-arm-9.patch"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;protocol=git;branch=linux-4.19.y"
SRC_URI += "https://source.denx.de/Xenomai/xenomai/-/archive/${XENO_V}/xenomai-${XENO_V}.tar.bz2"
SRC_URI += "file://${IPIPE_PATCH};apply=0"
SRC_URI += "file://0001-updated-DTS.patch"
SRC_URI += "file://defconfig"

COMPATIBLE_MACHINE = "raspberrypi3"

do_xenomai_prepare_kernel () {
  ${WORKDIR}/xenomai-${XENO_V}/scripts/prepare-kernel.sh --arch=arm --linux=${S} --ipipe=${WORKDIR}/${IPIPE_PATCH}
}

addtask xenomai_prepare_kernel after do_patch before do_configure
