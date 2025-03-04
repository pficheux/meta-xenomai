Tested with Yocto 4.1.4.

Use the following commands:

$ sudo apt install curl
$ mkdir ~/bin
$ curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
$ chmod a+x ~/bin/repo
$ mkdir ~/<path>/xilinx-2024.1
$ cd ~/<path>/xilinx-2024.1

# Fetch the manifest and checkout the target release version
$ ~/bin/repo init -u https://github.com/Xilinx/yocto-manifests.git -b rel-v2024.1

# Fetch all the source from the repositories in the manifest
$ ~/bin/repo sync

$ source setupsdk

$ MACHINE=zcu102-zynqmp bitbake petalinux-image-minimal

