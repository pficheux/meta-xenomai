DESCRIPTION = "Provides userspace xenomai support and libraries needed to for \
real-time applications using the xenomai RTOS implementation"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://include/COPYING;md5=79ed705ccb9481bf9e7026b99f4e2b0e"
SECTION = "xenomai"
HOMEPAGE = "http://www.xenomai.org/"
PR = "r0"

SRC_URI = "http://xenomai.org/downloads/xenomai/stable/xenomai-${PV}.tar.bz2"

S = "${WORKDIR}/xenomai-${PV}"

inherit autotools pkgconfig

includedir = "/usr/include/xenomai"

SRC_URI[md5sum] = "6d7760cd065359666b16bfdaf0f1f932"

PACKAGES += "${PN}-demos"

FILES_${PN}-demos = "/usr/demo"
FILES_${PN}-dev += "/dev"
FILES_${PN} += " \
  /usr/lib/modechk.wrappers \
  /usr/lib/cobalt.wrappers \
  /usr/lib/dynlist.ld \
"

EXTRA_OECONF += "--enable-smp \
                --with-core=cobalt"

