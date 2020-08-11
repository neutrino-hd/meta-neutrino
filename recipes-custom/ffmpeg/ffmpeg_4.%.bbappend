FILESEXTRAPATHS_prepend := "${THISDIR}/ffmpeg:"

DEPENDS_append += "libass libxml2 freetype rtmpdump"

SRC_URI_append =  " \
	file://4_02_fix_mpegts.patch \
	file://4_03_allow_to_choose_rtmp_impl_at_runtime.patch \
	file://4_04_hls_replace_key_uri.patch \
	file://4_06_optimize_aac.patch \
	file://4_07_increase_buffer_size.patch \
	file://4_08_recheck_discard_flags.patch \
	file://4_09_ffmpeg_fix_edit_list_parsing.patch \
	file://4_10_rtsp_patch \
	file://4_11_dxva2_patch \
	file://4_A02-corrupt-h264-frames.patch \
	file://4_A11-FFmpeg-devel-amfenc-Add-support-for-pict_type-field.patch \
"

PACKAGECONFIG[libass] = "--enable-libass,--disable-libass,libass"
PACKAGECONFIG[libbluray] = "--enable-libbluray --enable-protocol=bluray,--disable-libbluray,libbluray"
PACKAGECONFIG[libfreetype] = "--enable-libfreetype,--disable-libfreetype,freetype"
PACKAGECONFIG[librtmp] = "--enable-librtmp,--disable-librtmp,rtmpdump"
PACKAGECONFIG[wavpack] = "--enable-libwavpack,--disable-libwavpack,wavpack"
PACKAGECONFIG[x265] = "--enable-libx265,--disable-libx265,x265"
PACKAGECONFIG[webp] = "--enable-libwebp,--disable-libwebp,libwebp"

PACKAGECONFIG_append = " libass libbluray libfreetype librtmp libvorbis libwebp \
                        mp3lame openssl webp wavpack x265"

MIPSFPU = "${@bb.utils.contains('TARGET_FPU', 'soft', '--disable-mipsfpu', '--enable-mipsfpu', d)}"

EXTRA_FFCONF = " \
    --prefix=${prefix} \
    --disable-static \
    --disable-runtime-cpudetect \
    --enable-ffprobe \
    --disable-altivec \
    --disable-amd3dnow \
    --disable-amd3dnowext \
    --disable-mmx \
    --disable-mmxext \
    --disable-sse \
    --disable-sse2 \
    --disable-sse3 \
    --disable-ssse3 \
    --disable-sse4 \
    --disable-sse42 \
    --disable-avx \
    --disable-xop \
    --disable-fma3 \
    --disable-fma4 \
    --disable-avx2 \
    --enable-inline-asm \
    --enable-asm \
    --disable-x86asm \
    --disable-fast-unaligned \
    --enable-muxers \
    --enable-encoders \
    --enable-decoders \
    --enable-demuxers \
    --enable-parsers \
    --enable-bsfs \
    --enable-protocols \
    --enable-indevs \
    --enable-outdevs \
    --enable-filters \
    --disable-doc \
    --disable-htmlpages \
    --disable-manpages \
    --disable-podpages \
    --disable-txtpages \
    --disable-debug \
    ${@bb.utils.contains("TARGET_ARCH", "mipsel", "${MIPSFPU} --disable-vfp --disable-neon --disable-mipsdsp --disable-mipsdspr2", "", d)} \
    ${@bb.utils.contains("TARGET_ARCH", "arm", "--enable-armv6 --enable-armv6t2 --enable-vfp --enable-neon", "", d)} \
    ${@bb.utils.contains("TUNE_FEATURES", "aarch64", "--enable-armv8 --enable-vfp --enable-neon", "", d)} \
    --extra-cflags="${TARGET_CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS} -ffunction-sections -fdata-sections -fno-aggressive-loop-optimizations" \
    --extra-ldflags="${TARGET_LDFLAGS},--gc-sections -Wl,--print-gc-sections,-lrt" \
"

LICENSE_FLAGS_WHITELIST = "commercial"
