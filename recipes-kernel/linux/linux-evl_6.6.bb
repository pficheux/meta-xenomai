require recipes-kernel/linux/linux-yocto.inc

KBRANCH:zynqmp ?= "v6.6.y-evl-rebase"

SRCREV_meta ?= "221d09d9fea4885f4e86d1fc2e83c5767bed4b27"
SRCREV_machine ?= "2a8ab6641daa6d020531a1ac93c316cbf091afb5"
SRCREV ?= "${SRCREV_machine}"

SRC_URI = "git://source.denx.de/Xenomai/xenomai4/linux-evl.git;protocol=https;name=machine;branch=${KBRANCH}; \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-6.6;destsuffix=${KMETA}; \
           file://evl.cfg"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
LINUX_VERSION ?= "6.6.52"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"
DEPENDS += "gmp-native libmpc-native"
DEPENDS += "rsync-native"

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "1"

KERNEL_DEVICETREE:qemuarmv5 = "versatile-pb.dtb"

COMPATIBLE_MACHINE = "^(zynqmp)$"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
KERNEL_FEATURES:append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES:append:qemuall=" cfg/virtio.scc features/drm-bochs/drm-bochs.scc cfg/net/mdio.scc"
KERNEL_FEATURES:append:qemux86=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES:append:qemux86-64=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES:append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "", d)}"
KERNEL_FEATURES:append = " ${@bb.utils.contains("DISTRO_FEATURES", "ptest", " features/scsi/scsi-debug.scc", "", d)}"
KERNEL_FEATURES:append = " ${@bb.utils.contains("DISTRO_FEATURES", "ptest", " features/gpio/mockup.scc", "", d)}"
KERNEL_FEATURES:append:powerpc =" arch/powerpc/powerpc-debug.scc"
KERNEL_FEATURES:append:powerpc64 =" arch/powerpc/powerpc-debug.scc"
KERNEL_FEATURES:append:powerpc64le =" arch/powerpc/powerpc-debug.scc"

INSANE_SKIP:kernel-vmlinux:qemuppc64 = "textrel"

# Workaround from meta-xilinx/meta-xilinx-core/recipes-kernel/linux/linux-xlnx_2024.1.bb for:
#  rm: cannot remove '.../tmp/work/zynqmp_generic-xilinx-linux/linux-xlnx/6.6.0-xilinx-v2024.1+gitAUTOINC+340eed5001-r0/image/lib/modules/6.6.0-xilinx-v2024.1-g340eed500130/source': No such file or directory
# This will not be required Scarthgap
kernel_do_install:prepend () {
	mkdir -p "${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}"
	touch "${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/source"
}
