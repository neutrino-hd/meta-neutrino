#!/bin/sh

local_bindir="/etc/neutrino/bin"
bindir="/usr/bin"

for cam in oscam cccam gbox; do
        if [ -x "$local_bindir"/"$cam" ]; then
                if [ "$cam" = "oscam" ]; then
                        ls -lh "$bindir"/"$cam" | grep "internal" > /dev/null && ln -sf "$local_bindir"/"$cam" "$bindir"/"$cam"
                else
                        [ ! -L "$bindir"/"$cam" ] && ln -sf "$local_bindir"/"$cam" "$bindir"/"$cam"
                fi
        else
                if [ "$cam" = "oscam" ]; then
                        ls -lh "$bindir"/"$cam" | grep "internal" > /dev/null || ln -sf "$bindir"/"$cam".internal "$bindir"/"$cam"
                else
                        [ -L "$bindir"/"$cam" ] && rm -rf "$bindir"/"$cam"
                fi
        fi
done

exit 0

