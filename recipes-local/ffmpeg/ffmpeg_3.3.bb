SUMMARY = "A complete, cross-platform solution to record, convert and stream audio and video."
DESCRIPTION = "FFmpeg is the leading multimedia framework, able to decode, encode, transcode, \
               mux, demux, stream, filter and play pretty much anything that humans and machines \
               have created. It supports the most obscure ancient formats up to the cutting edge."
HOMEPAGE = "https://www.ffmpeg.org/"
SECTION = "libs"

LICENSE = "BSD & GPLv2+ & LGPLv2.1+ & MIT"
LICENSE_${PN} = "GPLv2+"
LICENSE_libavcodec = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libavdevice = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libavfilter = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libavformat = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libavresample = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libavutil = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libpostproc = "GPLv2+"
LICENSE_libswresample = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libswscale = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_FLAGS = "commercial"

LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LGPLv2.1;md5=bd7a443320af8c812e4c18d1b79df004 \
                    file://COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "https://www.ffmpeg.org/releases/${BP}.tar.xz \
           file://mips64_cpu_detection.patch \
          "
SRC_URI[md5sum] = "368f1fff4bdadaf2823934cc0aadd71d"
SRC_URI[sha256sum] = "599e7f7c017221c22011c4037b88bdcd1c47cd40c1e466838bc3c465f3e9569d"

# Build fails when thumb is enabled: https://bugzilla.yoctoproject.org/show_bug.cgi?id=7717
ARM_INSTRUCTION_SET = "arm"

# Should be API compatible with libav (which was a fork of ffmpeg)
# libpostproc was previously packaged from a separate recipe
PROVIDES = "libav"

DEPENDS = "alsa-lib zlib libogg yasm-native libass libbluray rtmpdump"

inherit autotools pkgconfig

PACKAGECONFIG ??= "avdevice avfilter avcodec avformat swresample swscale \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xv', '', d)}"

# libraries to build in addition to avutil
PACKAGECONFIG[avdevice] = "--enable-avdevice,--disable-avdevice"
PACKAGECONFIG[avfilter] = "--enable-avfilter,--disable-avfilter"
PACKAGECONFIG[avcodec] = "--enable-avcodec,--disable-avcodec"
PACKAGECONFIG[avformat] = "--enable-avformat,--disable-avformat"
PACKAGECONFIG[swresample] = "--enable-swresample,--disable-swresample"
PACKAGECONFIG[swscale] = "--enable-swscale,--disable-swscale"
PACKAGECONFIG[postproc] = "--enable-postproc,--disable-postproc"
PACKAGECONFIG[avresample] = "--enable-avresample,--disable-avresample"

# features to support
PACKAGECONFIG[bzlib] = "--enable-bzlib,--disable-bzlib,bzip2"
PACKAGECONFIG[gpl] = "--enable-gpl,--disable-gpl"
PACKAGECONFIG[gsm] = "--enable-libgsm,--disable-libgsm,libgsm"
PACKAGECONFIG[jack] = "--enable-indev=jack,--disable-indev=jack,jack"
PACKAGECONFIG[libvorbis] = "--enable-libvorbis,--disable-libvorbis,libvorbis"
PACKAGECONFIG[lzma] = "--enable-lzma,--disable-lzma,xz"
PACKAGECONFIG[mp3lame] = "--enable-libmp3lame,--disable-libmp3lame,lame"
PACKAGECONFIG[openssl] = "--enable-openssl,--disable-openssl,openssl"
PACKAGECONFIG[schroedinger] = "--enable-libschroedinger,--disable-libschroedinger,schroedinger"
PACKAGECONFIG[speex] = "--enable-libspeex,--disable-libspeex,speex"
PACKAGECONFIG[theora] = "--enable-libtheora,--disable-libtheora,libtheora"
PACKAGECONFIG[vaapi] = "--enable-vaapi,--disable-vaapi,libva"
PACKAGECONFIG[vdpau] = "--enable-vdpau,--disable-vdpau,libvdpau"
PACKAGECONFIG[vpx] = "--enable-libvpx,--disable-libvpx,libvpx"
PACKAGECONFIG[x264] = "--enable-libx264,--disable-libx264,x264"
PACKAGECONFIG[xv] = "--enable-outdev=xv,--disable-outdev=xv,libxv"

# Check codecs that require --enable-nonfree
USE_NONFREE = "${@bb.utils.contains_any('PACKAGECONFIG', [ 'openssl' ], 'yes', '', d)}"

def cpu(d):
    for arg in (d.getVar('TUNE_CCARGS', True) or '').split():
        if arg.startswith('-mcpu='):
            return arg[6:]
    return 'generic'

EXTRA_OECONF = " \
    --disable-stripping \
    --enable-pic \
    --enable-shared \
    --enable-pthreads \
    ${@bb.utils.contains('USE_NONFREE', 'yes', '--enable-nonfree', '', d)} \
    \
    --cross-prefix=${TARGET_PREFIX} \
    \
    --ld="${CCLD}" \
    --cc="${CC}" \
    --cxx="${CXX}" \
    --arch=${TARGET_ARCH} \
    --target-os="linux" \
    --enable-cross-compile \
    --extra-cflags="${TARGET_CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}" \
    --extra-ldflags="${TARGET_LDFLAGS}" \
    --sysroot="${STAGING_DIR_TARGET}" \
    --enable-hardcoded-tables \
    ${EXTRA_FFCONF} \
    --libdir=${libdir} \
    --shlibdir=${libdir} \
    --datadir=${datadir}/ffmpeg \
    ${@bb.utils.contains('AVAILTUNES', 'mips32r2', '', '--disable-mipsdsp --disable-mipsdspr2', d)} \
    --cpu=${@cpu(d)} \
"

EXTRA_OECONF_append += "--disable-doc \
            --disable-htmlpages \
            --disable-manpages \
            --disable-podpages \
            --disable-txtpages \
            \
            --disable-ffplay \
            --disable-ffserver \
            --disable-ffprobe \
            \
            --disable-altivec \
            --disable-mmx \
            \
            --disable-parsers \
                --enable-parser=aac \
                --enable-parser=aac_latm \
                --enable-parser=ac3 \
                --enable-parser=dca \
                --enable-parser=dvbsub \
                --enable-parser=dvdsub \
                --enable-parser=flac \
                --enable-parser=h264 \
                --enable-parser=mjpeg \
                --enable-parser=mpeg4video \
                --enable-parser=mpegaudio \
                --enable-parser=mpegvideo \
                --enable-parser=vc1 \
                --enable-parser=vorbis \
            \
            --disable-decoders \
                --enable-decoder=aac \
                --enable-decoder=ass \
                --enable-decoder=dca \
                --enable-decoder=dvbsub \
                --enable-decoder=dvdsub \
                --enable-decoder=flac \
                --enable-decoder=mjpeg \
                --enable-decoder=movtext \
                --enable-decoder=mp3 \
                --enable-decoder=pcm_s16le \
                --enable-decoder=pcm_s16le_planar \
                --enable-decoder=pgssub \
                --enable-decoder=srt \
                --enable-decoder=subrip \
                --enable-decoder=subviewer \
                --enable-decoder=subviewer1 \
                --enable-decoder=ssa \
                --enable-decoder=text \
                --enable-decoder=vorbis \
                --enable-decoder=xsub \
            \
            --disable-encoders \
            \
            --disable-demuxers \
                --enable-demuxer=aac \
                --enable-demuxer=ass \
                --enable-demuxer=ac3 \
                --enable-demuxer=avi \
                --enable-demuxer=dts \
                --enable-demuxer=flac \
                --enable-demuxer=flv \
                --enable-demuxer=hds \
                --enable-demuxer=hls \
		--enable-demuxer=image2 \
                --enable-demuxer=matroska \
                --enable-demuxer=mov \
                --enable-demuxer=mp3 \
                --enable-demuxer=mpegps \
                --enable-demuxer=mpegts \
                --enable-demuxer=mpegtsraw \
                --enable-demuxer=mpegvideo \
                --enable-demuxer=mpjpeg \
                --enable-demuxer=mjpeg \
                --enable-demuxer=ogg \
                --enable-demuxer=pcm_s16be \
                --enable-demuxer=pcm_s16le \
                --enable-demuxer=rm \
                --enable-demuxer=rtsp \
                --enable-demuxer=srt \
                --enable-demuxer=vc1 \
                --enable-demuxer=wav \
            \
            --disable-muxers \
                --enable-muxer=mpegts \
            \
            --disable-filters \
            \
            --disable-devices \
            \
            --disable-extra-warnings \
            --disable-postproc \
            \
            --enable-bsfs \
            --enable-libass \
            --enable-libbluray \
            --enable-librtmp \
            --enable-network \
            --enable-nonfree \
            --enable-openssl \
            --enable-swresample \
            \
            --disable-debug \
            --enable-cross-compile \
            --disable-static \
            --enable-shared \
            \
            --enable-encoder=mpeg2video \
            --enable-muxer=mpeg2video \
            --enable-filter=scale \
            --enable-hardcoded-tables \
"

do_configure() {
    ${S}/configure ${EXTRA_OECONF}
}

PACKAGES =+ "libavcodec \
             libavdevice \
             libavfilter \
             libavformat \
             libavresample \
             libavutil \
             libpostproc \
             libswresample \
             libswscale"

FILES_libavcodec = "${libdir}/libavcodec${SOLIBS}"
FILES_libavdevice = "${libdir}/libavdevice${SOLIBS}"
FILES_libavfilter = "${libdir}/libavfilter${SOLIBS}"
FILES_libavformat = "${libdir}/libavformat${SOLIBS}"
FILES_libavresample = "${libdir}/libavresample${SOLIBS}"
FILES_libavutil = "${libdir}/libavutil${SOLIBS}"
FILES_libpostproc = "${libdir}/libpostproc${SOLIBS}"
FILES_libswresample = "${libdir}/libswresample${SOLIBS}"
FILES_libswscale = "${libdir}/libswscale${SOLIBS}"

# ffmpeg disables PIC on some platforms (e.g. x86-32)
INSANE_SKIP_${MLPREFIX}libavcodec = "textrel"
INSANE_SKIP_${MLPREFIX}libavdevice = "textrel"
INSANE_SKIP_${MLPREFIX}libavfilter = "textrel"
INSANE_SKIP_${MLPREFIX}libavformat = "textrel"
INSANE_SKIP_${MLPREFIX}libavutil = "textrel"
INSANE_SKIP_${MLPREFIX}libavresample = "textrel"
INSANE_SKIP_${MLPREFIX}libswscale = "textrel"
INSANE_SKIP_${MLPREFIX}libswresample = "textrel"
INSANE_SKIP_${MLPREFIX}libpostproc = "textrel"
