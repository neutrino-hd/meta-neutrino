.POSIX:

INSTALL ?= all
SOURCES := subprocess.c liolib-copy.c
VERSION := 0.02
CFLAGS ?= -Wall -Wextra -pedantic -O2

.PHONY: all
all: subprocess.so

subprocess.so: $(SOURCES)
	$(CC) $(CFLAGS) -DOS_POSIX -shared -fPIC -o $@ $(SOURCES)

.PHONY: clean
clean:
	$(RM) subprocess.so

