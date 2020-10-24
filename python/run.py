import os

compareStrArr = (
          ["/trunk/WEB-INF/", "/WEB-INF/"] #jsp
        , ["/trunk/src/WebContent/", "/src/"] #java
        , ["/src/com/", "/src/"] #css
        , [".java", ".class"]
    )

print(compareStrArr[0][0])

f = open("filePath.txt", "r")
lines = f.readlines()

#문자열 정리
for line in lines:
    print(line)

print(lines[0])

#jar파일 저장할 폴더 생성
os.makedirs("deployjar", exist_ok=True)

#bat파일 생성
f = open("compression.bat", 'w')
f.write("jar cvf deployjar/deployJar.jar ")

#치환한 문자열들
f.write("JavaStudy/src/com/company/LamdaStudy.java")

f.close()
