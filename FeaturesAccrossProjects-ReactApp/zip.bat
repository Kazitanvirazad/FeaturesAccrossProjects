echo on

for /f "tokens=3,2,4 delims=/- " %%x in ("%date%") do set d=%%y%%x%%z

set data=%d%

Echo zipping...

"C:\Program Files\7-Zip\7z.exe" a -tzip "C:\Users\kazazad\Documents\StudyElements\Research_Development\dataentry\dataentry-react.zip" "C:\Users\kazazad\Documents\StudyElements\Research_Development\dataentry\dataentry-react\dist\"

echo Done!