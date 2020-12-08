-- The Tuxbox Copyright
--
-- Copyright 2018 The Tuxbox Project. All rights reserved.
--
-- Redistribution and use in source and binary forms, with or without modification, 
-- are permitted provided that the following conditions are met:
--
-- Redistributions of source code must retain the above copyright notice, this list
-- of conditions and the following disclaimer. Redistributions in binary form must
-- reproduce the above copyright notice, this list of conditions and the following
-- disclaimer in the documentation and/or other materials provided with the distribution.
--
-- THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS ``AS IS`` AND ANY EXPRESS OR IMPLIED
-- WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
-- AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
-- HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
-- EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
-- SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
-- HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
-- OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
-- SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
--
-- The views and conclusions contained in the software and documentation are those of the
-- authors and should not be interpreted as representing official policies, either expressed
-- or implied, of the Tuxbox Project.

caption = "AdvanceMame"

locale = {}
local g = {}

locale["deutsch"] = {
	start_mame = "Starte Mame Rom in: ",
	hint_txt = "AdvanceMame ist ein Emulator für Arcade Games"
}

locale["english"] = {
	start_mame = "Start mame rom in: ",
	hint_txt = "AdvanceMame is an emulator for arcade games"
}

neutrino_conf = configfile.new()
neutrino_conf:loadConfig("/etc/neutrino/config/neutrino.conf")
lang = neutrino_conf:getString("language", "english")

if locale[lang] == nil then
	lang = "english"
end

function basename(str)
	local name = string.gsub(str, "(.*/)(.*)", "%2")
	return name
end

function get_rom_path()
	for line in io.lines("/etc/advmame.rc") do
		if line:match("dir_rom") then
			local i,j = string.find(line, " ")
			rom_path = string.sub(line, j+1, #line)
		end
	end
	return rom_path .. "/"
end

function start_game(id, value)
	value = basename(value:sub(1, -5))
	os.execute("systemctl start advmame@" .. value)
end

function main_menu()
	g.main = menu.new{name=caption, icon="games"}
	m=g.main
	m:addItem{
		type="filebrowser",
		dir_mode="0",
		id=image_path,
		value=get_rom_path(),
		name=locale[lang].start_mame,
		action="start_game",
		icon="ok",
		directkey=RC["ok"],
		hint = locale[lang].hint_txt,
		hint_icon="hint_games"
		};
	m:exec()
	m:hide()
end

main_menu()
