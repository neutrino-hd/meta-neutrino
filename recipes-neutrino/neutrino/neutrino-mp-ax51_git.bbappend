do_configure_prepend() {
        ln -sf ${B}/src/gui/version.h ${S}/src/gui/
        if [ ${YT_DEV_KEY} ];then
                sed -i "s|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|${YT_DEV_KEY}|" ${S}/src/neutrino.cpp
        fi
        if [ ${TMDB_DEV_KEY} ];then
                sed -i "s|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|${TMDB_DEV_KEY}|" ${S}/src/neutrino.cpp
        fi
        if [ ${SHOUTCAST_DEV_KEY} ];then
                sed -i "s|XXXXXXXXXXXXXXXX|${SHOUTCAST_DEV_KEY}|" ${S}/src/neutrino.cpp
        fi
}

