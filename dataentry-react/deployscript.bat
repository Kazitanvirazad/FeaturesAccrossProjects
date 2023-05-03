echo "Deploying React App to maven webapp directory"
del C:\Users\kazazad\Documents\StudyElements\Research_Development\dataentry\dataentry\src\main\webapp\assets\ /E /Y
del C:\Users\kazazad\Documents\StudyElements\Research_Development\dataentry\dataentry\src\main\webapp\index.html
xcopy C:\Users\kazazad\Documents\StudyElements\Research_Development\dataentry\dataentry-react\dist C:\Users\kazazad\Documents\StudyElements\Research_Development\dataentry\dataentry\src\main\webapp /E /Y
