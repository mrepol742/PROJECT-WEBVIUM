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

call gradlew assembleRelease

cd "app\build\outputs\apk\release"

call zipalign -f -v 4 webvium-release.apk webvium-release.zipalign.apk

@rem %WEBVIUM_KEY% location to the keystore file
@rem %KEY_ALIAS% keystore alias
@rem %KS_PASS% keystore password
@rem %KEY_PASS% key password
call apksigner sign --ks %WEBVIUM_KEY% --ks-key-alias %KEY_ALIAS% --ks-pass pass:%KS_PASS% --key-pass pass:%KEY_PASS% --in webvium-release.zipalign.apk --out webvium-released.signed.apk

call apksigner verify webvium-released.signed.apk

@rem %WEBVIUM_PATH% location to the PROJECT-WEBVIUM
call explorer %WEBVIUM_PATH%\app\build\outputs\apk\release

call taskkill /F /IM java.exe /T
call taskkill /F /IM adb.exe /T