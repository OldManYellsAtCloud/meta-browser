# Copyright (C) 2009-2015, O.S. Systems Software Ltda. All Rights Reserved
# Released under the MIT license (see packages/COPYING)

include firefox.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/firefox-esr:"

SRC_URI[sha256sum] = "c0852a261be3be3c83865ec2c2a4aa65dc1ad6db7c70574926b63a8b48312919"

SRC_URI += "file://0001-Bug-1993797-Fix-AST-parsing-in-DecoratorVisitor-for-.patch \
            file://0001-Bug-1983713-Use-non-deprecated-ast-value.-r-firefox-.patch \
            file://0001-Bug-1983736-Patch-jsonschema-to-work-with-Python-3.1.patch \
            file://0001-Bug-1969769-Change-uses-of-ast.Str-with-ast.Constant.patch \
            file://undeclared-identifier__builtin_ia32_vcvtph2ps256.patch \
            file://clang22-nss-prelude-fix.patch \
            file://fix-musl-with-arm.patch \
"
