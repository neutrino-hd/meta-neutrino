#!/bin/sh

for cam in oscam ccam gbox; do
        if [ -f /etc/neutrino/bin/"$cam" ]; then
                ls -lh /usr/bin/"$cam" | grep "internal" > /dev/null && ln -sf /etc/neutrino/bin/"$cam" /usr/bin/"$cam"
        fi
done

[ ! -e /usr/bin/oscam ] && ln -sf /usr/bin/oscam.internal /usr/bin/oscam

exit 0
