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
salida7@Funcion DD ?
c@Variable DD ?
var@@aux51 DD ?
var@@aux38 DD ?
ab1@Variable DD ?
a@Variable DD ?
var@@aux82 DD ?
var@@aux40 DD ?
var@@aux43 DD ?
var@@aux89 DD ?
var@@aux25 DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@584 DB "Suma var = cte anda Long", 0
cadena@@585 DB "Suma var = cte no anda Long", 0
cadena@@586 DB "Suma var+var = -cte anda Long", 0
cadena@@587 DB "Suma var+var = -cte no anda Long", 0
cadena@@588 DB "Suma var+cte = -cte anda Long", 0
cadena@@589 DB "Suma var+cte = -cte no anda Long", 0
cadena@@590 DB "Suma cte+var = cte anda Long", 0
cadena@@591 DB "Suma cte+var = cte no anda Long", 0
cadena@@592 DB "Suma cte+-cte = cte anda Long", 0
cadena@@593 DB "Suma cte+-cte = cte no anda Long", 0
cadena@@594 DB "Suma -var+fn = -cte anda Long", 0
cadena@@595 DB "Suma -var+fn = -cte no anda Long", 0
cadena@@596 DB "Suma fn+var = cte anda Long", 0
cadena@@597 DB "Suma fn+var = cte no anda Long", 0
cadena@@598 DB "Suma -cte+fn = -cte anda Long", 0
cadena@@599 DB "Suma -cte+fn = -cte no anda Long", 0
cadena@@600 DB "Suma fn+-cte = cte anda Long", 0
cadena@@601 DB "Suma fn+-cte = cte no anda Long", 0
cadena@@602 DB "Suma fn+fn = cte anda Long", 0
cadena@@603 DB "Suma fn+fn = cte no anda Long", 0
cadena@@604 DB "Suma var+fn = var anda Long", 0
cadena@@605 DB "Suma var+fn = var no anda Long", 0
cadena@@606 DB "Suma -cte+cte = fn anda Long", 0
cadena@@607 DB "Suma -cte+cte = fn no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
MOV ab1@Variable,3
MOV EAX,ab1@Variable
MOV salida3@Funcion,EAX
RET
salida3 endp
salida10 proc
MOV ab1@Variable,10
MOV EAX,ab1@Variable
MOV salida10@Funcion,EAX
RET
salida10 endp
salida7 proc
MOV ab1@Variable,-7
MOV EAX,ab1@Variable
MOV salida7@Funcion,EAX
RET
salida7 endp
start:
MOV a@Variable,10
MOV b@Variable,-3
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,7
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@584, addr cadena@@584, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@585, addr cadena@@585, MB_OK
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
MOV b@Variable,3
MOV EAX,var@@aux11
MOV EDX,-7
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@586, addr cadena@@586, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@587, addr cadena@@587, MB_OK
Label17:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
Label17:
MOV a@Variable,-10
MOV EAX,var@@aux18
MOV EDX,-7
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@588, addr cadena@@588, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@589, addr cadena@@589, MB_OK
Label24:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
Label24:
MOV b@Variable,-3
MOV EAX,var@@aux25
MOV EDX,7
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@590, addr cadena@@590, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@591, addr cadena@@591, MB_OK
Label31:
MOV EAX,var@@aux31
MOV EDX,7
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@592, addr cadena@@592, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@593, addr cadena@@593, MB_OK
Label37:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
Label37:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
Label39:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
MOV a@Variable,-10
CALL salida3
MOV EAX,var@@aux43
MOV EDX,-7
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@594, addr cadena@@594, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@595, addr cadena@@595, MB_OK
Label49:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,10
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
MOV EDX,7
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@596, addr cadena@@596, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@597, addr cadena@@597, MB_OK
Label57:
CALL salida3
MOV EAX,var@@aux58
MOV EDX,-7
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@598, addr cadena@@598, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@599, addr cadena@@599, MB_OK
Label64:
CALL salida10
MOV EAX,var@@aux65
MOV EDX,7
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@600, addr cadena@@600, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@601, addr cadena@@601, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,var@@aux73
MOV EDX,13
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@602, addr cadena@@602, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@603, addr cadena@@603, MB_OK
Label79:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,10
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
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,10
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
MOV c@Variable,-7
CALL salida3
MOV EAX,var@@aux82
MOV EAX,c@Variable
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@604, addr cadena@@604, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@605, addr cadena@@605, MB_OK
Label88:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,a@Variable
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,10
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
CALL salida7
MOV EAX,var@@aux90
CMP EAX,salida7@Funcion
JNE Label96
invoke MessageBox, NULL, addr cadena@@606, addr cadena@@606, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@607, addr cadena@@607, MB_OK
Label97:
invoke ExitProcess, 0
end start