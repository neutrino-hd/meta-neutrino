RSUGGESTS_${PN} = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://ffmpeg-fix-hls.patch \
    file://ffmpeg-buffer-size.patch \
    file://ffmpeg-aac.patch \
    file://ffmpeg-fix-mpegts.patch \
    file://000003_allow_to_choose_rtmp_impl_at_runtime.patch \
    file://ffmpeg-fix-edit-list-parsing.patch \
    file://000001_add_dash_demux.patch \
"

