SUMMARY = "WebAssembly SDK"
HOMEPAGE = "https://github.com/WebAssembly/wasi-sdk"
DESCRIPTION = "SDK for WebAssembly System Interface (WASI)"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=a1ba2b4c4f909ac0b517d8a37d2ac70f"

inherit cmake native

DEPENDS = "clang-native ninja-native wasm-component-ld-native compiler-rt-native"

SRC_URI = "gitsm://github.com/WebAssembly/wasi-sdk.git;protocol=https;branch=main \
           file://0001-lower-cmake-requirement.patch \
           file://dont-build-wasm-component-ld.patch \
           file://dont-strip-toolchain.patch"

#SRC_URI += "file://0001-disable-exceptions-and-bulk-memory.patch \
#            file://0001-allow-compiling-with-cmake-4.patch"
# file://0001-install-sdk-to-regular-sysroot.patch
# file://dont-strip-toolchain.patch

SRCREV = "fbdec30656d4099da5c7fd7e8275e4af777ca97e"

SRC_URI[sha256sum] = "9e0f382110a3cf9196f02432c8f2e54d151515de36f9311c8c16073f6e6b16d3"

TOOLCHAIN = "clang"
TOOLCHAIN:class-native = "clang"

RUNTIME = "llvm"
RUNTIME:class-native = "llvm"

LD = "wasm-ld "
BUILD_LD = "wasm-ld "

EXTRA_OECMAKE = "-DWASI_SDK_BUILD_TOOLCHAIN=ON"

do_install:append() {
    # move everything from /usr to /usr/share/wasi-sdk
    install -d ${D}${base_prefix}/wasi-sysroot/
    mv ${D}${prefix}/* ${D}${base_prefix}/wasi-sysroot/

    install -d ${D}${datadir}
    mv ${D}${base_prefix}/wasi-sysroot ${D}${datadir}/

    # wasi sysroot building requires libedit
    ln -s ../../../lib/libedit.so.0 ${D}${datadir}/wasi-sysroot/lib/libedit.so.0
    ln -s ../../../lib/libtinfo.so.5 ${D}${datadir}/wasi-sysroot/lib/libtinfo.so.5
}
