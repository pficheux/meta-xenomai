DESCRIPTION = "Userspace Xenomai"
SECTION = "xenomai"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://include/COPYING;md5=79ed705ccb9481bf9e7026b99f4e2b0e"

SRC_URI = "https://source.denx.de/Xenomai/xenomai/-/archive/v3.2.1/xenomai-${PV}.tar.bz2"
SRC_URI += "file://0001-Updated-xeno-config-.in-use-CC.patch"

SRC_URI[md5sum] = "ce1bf6f6ec5c01dc17e184003f740321"

S = "${WORKDIR}/xenomai-${PV}"

inherit autotools pkgconfig

includedir = "/usr/include/xenomai"

PACKAGES += "${PN}-demos"
FILES:${PN}-demos = "/usr/demo"

FILES:${PN}-dev += "/dev"

FILES:${PN} += " \
  /usr/lib/modechk.wrappers \
  /usr/lib/cobalt.wrappers \
  /usr/lib/dynlist.ld \
"

FILES:${PN} += "/usr/share/"

EXTRA_OECONF += "--enable-smp --with-core=cobalt"
