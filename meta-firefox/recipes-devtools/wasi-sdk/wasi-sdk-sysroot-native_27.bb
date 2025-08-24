SUMMARY = "WebAssembly SDK"
HOMEPAGE = "https://github.com/WebAssembly/wasi-sdk"
DESCRIPTION = "SDK for WebAssembly System Interface (WASI)"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=a1ba2b4c4f909ac0b517d8a37d2ac70f"

inherit cmake native

DEPENDS = "wasm-component-ld-native wasi-sdk-toolchain-native"

SRC_URI = "gitsm://github.com/WebAssembly/wasi-sdk.git;protocol=https;branch=main \
           file://0001-lower-cmake-requirement.patch \
           file://dont-build-wasm-component-ld.patch"

#SRC_URI += "file://0001-disable-exceptions-and-bulk-memory.patch \
#            file://0001-allow-compiling-with-cmake-4.patch"
# file://0001-install-sdk-to-regular-sysroot.patch

SRCREV = "fbdec30656d4099da5c7fd7e8275e4af777ca97e"

SRC_URI[sha256sum] = "9e0f382110a3cf9196f02432c8f2e54d151515de36f9311c8c16073f6e6b16d3"

TOOLCHAIN = "clang"
TOOLCHAIN:class-native = "clang"

#RUNTIME = "llvm"
#RUNTIME:class-native = "llvm"

LD = "wasm-ld "
BUILD_LD = "wasm-ld "

EXTRA_OECMAKE = "-DCMAKE_TOOLCHAIN_FILE=${STAGING_DATADIR_NATIVE}/wasi-sysroot/share/cmake/wasi-sdk.cmake -DCMAKE_C_COMPILER_WORKS=ON -DCMAKE_CXX_COMPILER_WORKS=ON -DCMAKE_SYSROOT=${STAGING_DATADIR_NATIVE}/wasi-sysroot"

# it is built for wasm32-wasi target, not for build host
CC:remove = "${HOST_CC_ARCH} -Wl,--enable-new-dtags -Wl,-rpath-link,${STAGING_LIBDIR_NATIVE} -Wl,-rpath-link,${STAGING_BASE_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_BASE_LIBDIR_NATIVE} -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${UNINATIVE_LOADER}"
CXX:remove = "${HOST_CC_ARCH} -Wl,--enable-new-dtags -Wl,-rpath-link,${STAGING_LIBDIR_NATIVE} -Wl,-rpath-link,${STAGING_BASE_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_BASE_LIBDIR_NATIVE} -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${UNINATIVE_LOADER}"
CCLD:remove = "${HOST_CC_ARCH} -Wl,--enable-new-dtags -Wl,-rpath-link,${STAGING_LIBDIR_NATIVE} -Wl,-rpath-link,${STAGING_BASE_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_BASE_LIBDIR_NATIVE} -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${UNINATIVE_LOADER}"
CPP:remove = "${HOST_CC_ARCH} -Wl,--enable-new-dtags -Wl,-rpath-link,${STAGING_LIBDIR_NATIVE} -Wl,-rpath-link,${STAGING_BASE_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_BASE_LIBDIR_NATIVE} -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${UNINATIVE_LOADER}"
LDFLAGS:remove = "${HOST_CC_ARCH} -Wl,--enable-new-dtags -Wl,-rpath-link,${STAGING_LIBDIR_NATIVE} -Wl,-rpath-link,${STAGING_BASE_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_BASE_LIBDIR_NATIVE} -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${UNINATIVE_LOADER} ${COMPILER_RT}"

OECMAKE_C_FLAGS:remove = "${TOOLCHAIN_OPTIONS} ${HOST_CC_ARCH} -Wl,--enable-new-dtags -Wl,-rpath-link,${STAGING_LIBDIR_NATIVE} -Wl,-rpath-link,${STAGING_BASE_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_BASE_LIBDIR_NATIVE} -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${UNINATIVE_LOADER}"
OECMAKE_CXX_FLAGS:remove = "${TOOLCHAIN_OPTIONS} ${HOST_CC_ARCH} -Wl,--enable-new-dtags -Wl,-rpath-link,${STAGING_LIBDIR_NATIVE} -Wl,-rpath-link,${STAGING_BASE_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_BASE_LIBDIR_NATIVE} -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${UNINATIVE_LOADER}"

OECMAKE_C_LINK_FLAGS:remove = "${HOST_CC_ARCH} -Wl,--enable-new-dtags -Wl,-rpath-link,${STAGING_LIBDIR_NATIVE} -Wl,-rpath-link,${STAGING_BASE_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_BASE_LIBDIR_NATIVE} -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${UNINATIVE_LOADER}"
OECMAKE_CXX_LINK_FLAGS:remove = "${HOST_CC_ARCH} -Wl,--enable-new-dtags -Wl,-rpath-link,${STAGING_LIBDIR_NATIVE} -Wl,-rpath-link,${STAGING_BASE_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_LIBDIR_NATIVE} -Wl,-rpath,${STAGING_BASE_LIBDIR_NATIVE} -Wl,--allow-shlib-undefined -Wl,--dynamic-linker=${UNINATIVE_LOADER}"

LDFLAGS:remove = "-Wl,--hash-style=gnu -Wl,--as-needed -Wl,-z,relro,-z,now"



#do_install:append() {
#    # move everything from /usr to /usr/share/wasi-sdk
#    install -d ${D}${base_prefix}/wasi-sysroot/usr
#    mv ${D}${prefix}/* ${D}${base_prefix}/wasi-sysroot/usr/
#
#    install -d ${D}${datadir}
#    mv ${D}${base_prefix}/wasi-sysroot ${D}${datadir}/
#}

do_install:append() {
    mv ${D}${prefix}/clang-resource-dir ${D}${datadir}/wasi-sysroot/
}
