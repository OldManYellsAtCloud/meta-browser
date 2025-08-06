# Copyright (C) 2009-2015, O.S. Systems Software Ltda. All Rights Reserved
# Released under the MIT license (see packages/COPYING)

include firefox_crates_latest.inc
include firefox.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/firefox-latest:"

SRC_URI += "git://github.com/gfx-rs/wgpu.git;protocol=https;branch=trunk;name=wgpu;destsuffix=wgpu"

SRC_URI[sha256sum] = "c33937fe2f6ad29af3de8f1a128c054afbd64821f702bf98d9f4079b97d37f3a"

SRCREV_FORMAT .= "_application-services"
SRCREV_application-services = "4d9d334a28f61f1be84c92007aa0e76f04d165f4"

SRCREV_FORMAT .= "_midir"
SRCREV_midir = "37ad39de3382c2aad0758dab49ba545331a2257d"

SRCREV_FORMAT .= "_mp4parse"
SRCREV_mp4parse = "d3e4d255bd149d341c7e90f5e9fc84e743a8e179"

SRCREV_FORMAT .= "_wgpu"
SRCREV_wgpu = "3a5d0f2747fd43a0c4db8d911be1aca510c9ee82"
