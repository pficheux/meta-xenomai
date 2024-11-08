SUMMARY = "Xenomai 4"
DESCRIPTION = "Xenomai 4 user support (libevl)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2500c28da9dacbee31a3f4d4f69b74d0"

SRCBRANCH = "master"

SRC_URI = "git://source.denx.de/Xenomai/xenomai4/libevl.git;protocol=https;branch=${SRCBRANCH}"
SRCREV = "3bbe1d092e021113b1aa238c7a51c8f634c6cdcc"
PR = "47"

S = "${WORKDIR}/git"

# We need the kernel sources to be available, so build it first.
DEPENDS += "virtual/kernel"

# We may need this for 64bit atomic ops.
RDEPENDS:${PN}:append:libc-glibc = " libgcc"

inherit pkgconfig meson

PACKAGECONFIG ?= ""
PACKAGECONFIG[debug] = "-Doptimization=0 -Ddebug=true,,,"

EXTRA_OEMESON:append = "-Duapi=${STAGING_KERNEL_DIR}"

PACKAGES += "${PN}-test"

FILES:${PN}-test = "${prefix}/tests"

# install the test by default and use BAD_RECOMMENDATIONS when not desired
RRECOMMENDS:${PN} += "${PN}-test"

BBCLASSEXTEND = "native nativesdk"
