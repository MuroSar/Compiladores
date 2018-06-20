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
salida13@Funcion DD ?
var@@aux25 DD ?
var@@aux73 DD ?
b@Variable DD ?
a@Variable DD ?
var@@aux31 DD ?
var@@aux38 DD ?
c@Variable DD ?
salida3@Funcion DD ?
var@@aux89 DD ?
var@@aux82 DD ?
var@@aux43 DD ?
var@@aux40 DD ?
var@@aux11 DD ?
var@@aux51 DD ?
var@@aux90 DD ?
var@@aux58 DD ?
ab1@Variable DD ?
var@@aux2 DD ?
var@@aux18 DD ?
var@@aux65 DD ?
cadena@@80 DB "Suma var = -cte anda Long", 0
cadena@@81 DB "Suma var = -cte no anda Long", 0
cadena@@82 DB "Suma var+var = -cte anda Long", 0
cadena@@83 DB "Suma var+var = -cte no anda Long", 0
cadena@@84 DB "Suma var+-cte = -cte anda Long", 0
cadena@@85 DB "Suma var+-cte = -cte no anda Long", 0
cadena@@86 DB "Suma -cte+var = -cte anda Long", 0
cadena@@87 DB "Suma -cte+var = -cte no anda Long", 0
cadena@@88 DB "Suma -cte+-cte = -cte anda Long", 0
cadena@@89 DB "Suma -cte+-cte = -cte no anda Long", 0
cadena@@90 DB "Suma var+fn = -cte anda Long", 0
cadena@@91 DB "Suma var+fn = -cte no anda Long", 0
cadena@@92 DB "Suma fn+var = -cte anda Long", 0
cadena@@93 DB "Suma fn+var = -cte no anda Long", 0
cadena@@94 DB "Suma -cte+fn = -cte anda Long", 0
cadena@@95 DB "Suma -cte+fn = -cte no anda Long", 0
cadena@@96 DB "Suma fn+-cte = -cte anda Long", 0
cadena@@97 DB "Suma fn+-cte = -cte no anda Long", 0
cadena@@98 DB "Suma fn+fn = -cte anda Long", 0
cadena@@99 DB "Suma fn+fn = -cte no anda Long", 0
cadena@@100 DB "Suma var+fn = var anda Long", 0
cadena@@101 DB "Suma var+fn = var no anda Long", 0
cadena@@102 DB "Suma -cte+-cte = fn anda Long", 0
cadena@@103 DB "Suma -cte+-cte = fn no anda Long", 0
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
invoke MessageBox, NULL, addr cadena@@80, addr cadena@@80, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@81, addr cadena@@81, MB_OK
Label9:
MOV a@Variable,-10
MOV b@Variable,-3
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,-13
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@82, addr cadena@@82, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@83, addr cadena@@83, MB_OK
Label17:
MOV a@Variable,-10
MOV EAX,a@Variable
ADD EAX,-3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-13
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@84, addr cadena@@84, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@85, addr cadena@@85, MB_OK
Label24:
MOV b@Variable,-3
MOV EAX,-10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,-13
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@86, addr cadena@@86, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@87, addr cadena@@87, MB_OK
Label31:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,-13
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@88, addr cadena@@88, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@89, addr cadena@@89, MB_OK
Label37:
Label39:
MOV a@Variable,-10
CALL salida3
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,-13
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@90, addr cadena@@90, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@91, addr cadena@@91, MB_OK
Label49:
MOV b@Variable,-3
CALL salida10
MOV EAX,salida10@Funcion
ADD EAX,b@Variable
JO _overflow
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,-13
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@92, addr cadena@@92, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@93, addr cadena@@93, MB_OK
Label57:
CALL salida3
MOV EAX,-10
ADD EAX,salida3@Funcion
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,-13
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@94, addr cadena@@94, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@95, addr cadena@@95, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion
ADD EAX,-3
JO _overflow
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,-13
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@96, addr cadena@@96, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@97, addr cadena@@97, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion
ADD EAX,salida3@Funcion
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,-13
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@98, addr cadena@@98, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@99, addr cadena@@99, MB_OK
Label79:
MOV a@Variable,-10
MOV c@Variable,-13
CALL salida3
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@100, addr cadena@@100, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@101, addr cadena@@101, MB_OK
Label88:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux90,EAX
CALL salida13
MOV EAX,var@@aux90
CMP EAX,salida13@Funcion
JNE Label96
invoke MessageBox, NULL, addr cadena@@102, addr cadena@@102, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@103, addr cadena@@103, MB_OK
Label97:
invoke ExitProcess, 0
end start