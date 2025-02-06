FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += '${@ "file://0004-Copy-missing-header-to-include-folder.patch" \
               if "${LLVMVERSION}".startswith("14") else ""}'
