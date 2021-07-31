@rem
@rem
@rem Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
@rem
@rem License under the GNU General Public License, Version 3.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem     https://www.gnu.org/licenses/gpl-3.0.en.html
@rem
@rem Unless required by the applicable law or agreed in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@rem %WIKI_WEBVIUM% location to the wiki folder
@rem %WEBVIUM_PATH% location to the PROJECT-WEBVIUM folder
javadoc -d %WIKI_WEBVIUM% -doctitle "PROJECT-WEBVIUM" -windowtitle "PROJECT-WEBVIUM Documentation" -footer "<a href="https://mrepol742.github.io">Copyright (c) 2021. Melvin Jones Repol</a>" -keywords -sourcepath %WEBVIUM_PATH%\app\src\main\java -stylesheetfile %WEBVIUM_PATH%\stylesheet.css -top "Download PROJECT-WEBVIUM <a href="https://mrepol742.github.io/PROJECT-WEBVIUM">https://mrepol742.github.io/PROJECT-WEBVIUM</a>" -bottom "PROJECT-WEBVIUM was Programmed by <a href="https://mrepol742.github.io">mrepol742</a> with assistance of <a href="SamiunNafis.github.io">SamiunNafis</a>" -header "PROJECT-WEBVIUM" -notimestamp -subpackages com
cd %WIKI_WEBVIUM%
git add .
git commit -m "Update JavaDoc"
git push origin master