.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__CERO DQ 0.0
__MIN_DOUBLE DQ 2.2250738585072014E-308
__MAX_DOUBLE DQ 1.7976931348623157E308
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
salida10@Funcion DD ?
var@@aux58 DD ?
var@@aux18 DD ?
var@@aux2 DD ?
var@@aux65 DD ?
b@Variable DD ?
salida3@Funcion DD ?
c@Variable DD ?
var@@aux51 DD ?
var@@aux38 DD ?
ab1@Variable DD ?
a@Variable DD ?
salida13@Funcion DD ?
var@@aux82 DD ?
var@@aux40 DD ?
var@@aux43 DD ?
var@@aux89 DD ?
var@@aux25 DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@532 DB "Suma var = -cte anda Long", 0
cadena@@533 DB "Suma var = -cte no anda Long", 0
cadena@@534 DB "Suma var+var = -cte anda Long", 0
cadena@@535 DB "Suma var+var = -cte no anda Long", 0
cadena@@536 DB "Suma var+-cte = -cte anda Long", 0
cadena@@537 DB "Suma var+-cte = -cte no anda Long", 0
cadena@@538 DB "Suma -cte+var = -cte anda Long", 0
cadena@@539 DB "Suma -cte+var = -cte no anda Long", 0
cadena@@540 DB "Suma -cte+-cte = -cte anda Long", 0
cadena@@541 DB "Suma -cte+-cte = -cte no anda Long", 0
cadena@@542 DB "Suma var+fn = -cte anda Long", 0
cadena@@543 DB "Suma var+fn = -cte no anda Long", 0
cadena@@544 DB "Suma fn+var = -cte anda Long", 0
cadena@@545 DB "Suma fn+var = -cte no anda Long", 0
cadena@@546 DB "Suma -cte+fn = -cte anda Long", 0
cadena@@547 DB "Suma -cte+fn = -cte no anda Long", 0
cadena@@548 DB "Suma fn+-cte = -cte anda Long", 0
cadena@@549 DB "Suma fn+-cte = -cte no anda Long", 0
cadena@@550 DB "Suma fn+fn = -cte anda Long", 0
cadena@@551 DB "Suma fn+fn = -cte no anda Long", 0
cadena@@552 DB "Suma var+fn = var anda Long", 0
cadena@@553 DB "Suma var+fn = var no anda Long", 0
cadena@@554 DB "Suma -cte+-cte = fn anda Long", 0
cadena@@555 DB "Suma -cte+-cte = fn no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
MOV ab1@Variable,-3
MOV EAX,ab1@Variable
MOV salida3@Funcion,EAX
RET
salida3 endp
salida10 proc
MOV ab1@Variable,-10
MOV EAX,ab1@Variable
MOV salida10@Funcion,EAX
RET
salida10 endp
salida13 proc
MOV ab1@Variable,-13
MOV EAX,ab1@Variable
MOV salida13@Funcion,EAX
RET
salida13 endp
start:
MOV a@Variable,-10
MOV b@Variable,-3
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,-13
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@532, addr cadena@@532, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@533, addr cadena@@533, MB_OK
Label9:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
Label9:
MOV a@Variable,-10
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV b@Variable,-3
MOV EAX,var@@aux11
MOV EDX,-13
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@534, addr cadena@@534, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@535, addr cadena@@535, MB_OK
Label17:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
Label17:
MOV a@Variable,-10
MOV EAX,var@@aux18
MOV EDX,-13
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@536, addr cadena@@536, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@537, addr cadena@@537, MB_OK
Label24:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,-3
JO _overflow
MOV var@@aux18,EAX
Label24:
MOV b@Variable,-3
MOV EAX,var@@aux25
MOV EDX,-13
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@538, addr cadena@@538, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@539, addr cadena@@539, MB_OK
Label31:
MOV EAX,var@@aux31
MOV EDX,-13
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@540, addr cadena@@540, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@541, addr cadena@@541, MB_OK
Label37:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,-3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,-10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
Label37:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,-3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,-10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
Label39:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,-3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,-10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
MOV a@Variable,-10
CALL salida3
MOV EAX,var@@aux43
MOV EDX,-13
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@542, addr cadena@@542, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@543, addr cadena@@543, MB_OK
Label49:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,-3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,-10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux43,EAX
Label49:
MOV b@Variable,-3
CALL salida10
MOV EAX,var@@aux51
MOV EDX,-13
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@544, addr cadena@@544, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@545, addr cadena@@545, MB_OK
Label57:
CALL salida3
MOV EAX,var@@aux58
MOV EDX,-13
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@546, addr cadena@@546, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@547, addr cadena@@547, MB_OK
Label64:
CALL salida10
MOV EAX,var@@aux65
MOV EDX,-13
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@548, addr cadena@@548, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@549, addr cadena@@549, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,var@@aux73
MOV EDX,-13
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@550, addr cadena@@550, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@551, addr cadena@@551, MB_OK
Label79:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,-3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,-10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux43,EAX
MOV EAX,salida10@Funcion
ADD EAX,b@Variable
JO _overflow
MOV var@@aux51,EAX
MOV EAX,-10
ADD EAX,salida3@Funcion
MOV var@@aux58,EAX
MOV EAX,salida10@Funcion
ADD EAX,-3
JO _overflow
MOV var@@aux65,EAX
MOV EAX,salida10@Funcion
ADD EAX,salida3@Funcion
MOV var@@aux73,EAX
Label79:
MOV a@Variable,-10
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,-3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,-10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux43,EAX
MOV EAX,salida10@Funcion
ADD EAX,b@Variable
JO _overflow
MOV var@@aux51,EAX
MOV EAX,-10
ADD EAX,salida3@Funcion
MOV var@@aux58,EAX
MOV EAX,salida10@Funcion
ADD EAX,-3
JO _overflow
MOV var@@aux65,EAX
MOV EAX,salida10@Funcion
ADD EAX,salida3@Funcion
MOV var@@aux73,EAX
MOV c@Variable,-13
CALL salida3
MOV EAX,var@@aux82
MOV EAX,c@Variable
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@552, addr cadena@@552, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@553, addr cadena@@553, MB_OK
Label88:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,-3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,-10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux43,EAX
MOV EAX,salida10@Funcion
ADD EAX,b@Variable
JO _overflow
MOV var@@aux51,EAX
MOV EAX,-10
ADD EAX,salida3@Funcion
MOV var@@aux58,EAX
MOV EAX,salida10@Funcion
ADD EAX,-3
JO _overflow
MOV var@@aux65,EAX
MOV EAX,salida10@Funcion
ADD EAX,salida3@Funcion
MOV var@@aux73,EAX
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux82,EAX
Label88:
CALL salida13
MOV EAX,var@@aux90
CMP EAX,salida13@Funcion
JNE Label96
invoke MessageBox, NULL, addr cadena@@554, addr cadena@@554, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@555, addr cadena@@555, MB_OK
Label97:
invoke ExitProcess, 0
end start