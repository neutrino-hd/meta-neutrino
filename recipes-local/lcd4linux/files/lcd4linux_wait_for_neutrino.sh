#! /bin/sh

monitordir="/tmp/lcd"
file="$monitordir/layout"

[ -d "$monitordir" ] || mkdir -p "$monitordir"

while [ ! -f "$file" ]; do
	inotifywait -e create -e modify "$monitordir"
done

exit 0
