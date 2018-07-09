DESCRIPTION = "Provides userspace xenomai support and libraries needed to for \
real-time applications using the xenomai RTOS implementation (3.0.6)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://include/COPYING;md5=79ed705ccb9481bf9e7026b99f4e2b0e"
SECTION = "xenomai"
HOMEPAGE = "http://www.xenomai.org/"
PR = "r0"

SRC_URI = "http://xenomai.org/downloads/xenomai/stable/xenomai-3.0.6.tar.bz2"

S = "${WORKDIR}/xenomai-${PV}"

inherit autotools pkgconfig

includedir = "/usr/include/xenomai"

SRC_URI[md5sum] = "6017203d0992bb5334498c196bf6f25d"

EXTRA_OECONF += "--enable-smp \
                --with-core=cobalt"

#Fixes QA Issues: non debug package contains .debug directory
FILES_${PN}-dbg += "/usr/bin/regression/posix/.debug"
FILES_${PN}-dbg += "/usr/bin/regression/native/.debug"
FILES_${PN}-dbg += "/usr/bin/regression/native+posix/.debug"
FILES_${PN}-dbg += "/usr/demo/.debug/*"

# Fixes QA Error - Non -dev package contains symlink .so
FILES_${PN}-dev += "/usr/lib/*.so"

#Add directories to package for shipping
FILES_${PN} += "/dev"
FILES_${PN} += "/usr/bin/*"
FILES_${PN} += "/usr/lib/*"
FILES_${PN} += "/usr/sbin/*"
FILES_${PN} += "/usr/include/*"
FILES_${PN} += "/usr/lib/*"
FILES_${PN} += "/usr/share/doc/*"
FILES_${PN} += "/usr/share/man/*"
FILES_${PN} += "/usr/demo/*"

INSANE_SKIP_${PN} += "ldflags"

