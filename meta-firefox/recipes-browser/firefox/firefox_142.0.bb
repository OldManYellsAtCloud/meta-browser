# Copyright (C) 2009-2015, O.S. Systems Software Ltda. All Rights Reserved
# Released under the MIT license (see packages/COPYING)

include firefox_crates_latest.inc
include firefox.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/firefox-latest:"

SRC_URI += "git://github.com/gfx-rs/wgpu.git;protocol=https;branch=trunk;name=wgpu;destsuffix=wgpu"

SRC_URI[sha256sum] = "2808e1f10f3b6c593f96f3745a55d12c98107886daef00f1e354c24203a64b0f"

SRCREV_FORMAT .= "_application-services"
SRCREV_application-services = "b5175a2a2458834c9fdc0468d851658b0657f95b"

SRCREV_FORMAT .= "_midir"
SRCREV_midir = "37ad39de3382c2aad0758dab49ba545331a2257d"

SRCREV_FORMAT .= "_mp4parse"
SRCREV_mp4parse = "d3e4d255bd149d341c7e90f5e9fc84e743a8e179"

SRCREV_FORMAT .= "_neqo"
SRCREV_neqo = "531cb0b6c21d27ff1ccec5502ecc346a6a922d71"

SRCREV_FORMAT .= "_wgpu"
SRCREV_wgpu = "b83c9cfd578837a6163d980130249c245a9c5f8a"
