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
var@@aux90 DD ?
c@Variable DD ?
var@@aux51 DD ?
var@@aux11 DD ?
var@@aux38 DD ?
ab1@Variable DD ?
a@Variable DD ?
var@@aux82 DD ?
salida30@Funcion DD ?
var@@aux40 DD ?
var@@aux43 DD ?
var@@aux89 DD ?
var@@aux25 DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@148 DB "Multiplicación var = -cte anda Long", 0
cadena@@149 DB "Multiplicación var = -cte no anda Long", 0
cadena@@150 DB "Multiplicación var*var = -cte anda Long", 0
cadena@@151 DB "Multiplicación var*var = -cte no anda Long", 0
cadena@@152 DB "Multiplicación var*cte = -cte anda Long", 0
cadena@@153 DB "Multiplicación var*cte = -cte no anda Long", 0
cadena@@154 DB "Multiplicación -cte*var = -cte anda Long", 0
cadena@@155 DB "Multiplicación -cte*var = -cte no anda Long", 0
cadena@@156 DB "Multiplicación cte*-cte = -cte anda Long", 0
cadena@@157 DB "Multiplicación cte*-cte = -cte no anda Long", 0
cadena@@158 DB "Multiplicación var*fn = -cte anda Long", 0
cadena@@159 DB "Multiplicación var*fn = -cte no anda Long", 0
cadena@@160 DB "Multiplicación fn*var = -cte anda Long", 0
cadena@@161 DB "Multiplicación fn*var = -cte no anda Long", 0
cadena@@162 DB "Multiplicación cte*fn = -cte anda Long", 0
cadena@@163 DB "Multiplicación cte*fn = -cte no anda Long", 0
cadena@@164 DB "Multiplicación fn*-cte = -cte anda Long", 0
cadena@@165 DB "Multiplicación fn*-cte = -cte no anda Long", 0
cadena@@166 DB "Multiplicación fn*fn = -cte anda Long", 0
cadena@@167 DB "Multiplicación fn*fn = -cte no anda Long", 0
cadena@@168 DB "Multiplicación var*fn = var anda Long", 0
cadena@@169 DB "Multiplicación var*fn = var no anda Long", 0
cadena@@170 DB "Multiplicación cte*-cte = fn anda Long", 0
cadena@@171 DB "Multiplicación cte*-cte = fn no anda Long", 0
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
MOV ab1@Variable,10
MOV EAX,ab1@Variable
MOV salida10@Funcion,EAX
RET
salida10 endp
salida30 proc
MOV ab1@Variable,-30
MOV EAX,ab1@Variable
MOV salida30@Funcion,EAX
RET
salida30 endp
start:
MOV a@Variable,-10
MOV b@Variable,3
MOV EAX,a@Variable
MOV EDX,b@Variable
MUL EDX
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,-30
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@148, addr cadena@@148, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@149, addr cadena@@149, MB_OK
Label9:
MOV a@Variable,-10
MOV b@Variable,3
MOV EAX,a@Variable
MOV EDX,b@Variable
MUL EDX
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,-30
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@150, addr cadena@@150, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@151, addr cadena@@151, MB_OK
Label17:
MOV a@Variable,-10
MOV EAX,a@Variable
MOV EDX,3
MUL EDX
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-30
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@152, addr cadena@@152, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@153, addr cadena@@153, MB_OK
Label24:
MOV b@Variable,3
MOV EAX,-10
MOV EDX,b@Variable
MUL EDX
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,-30
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@154, addr cadena@@154, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@155, addr cadena@@155, MB_OK
Label31:
MOV EAX,10
MOV EDX,-3
MUL EDX
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,-30
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@156, addr cadena@@156, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@157, addr cadena@@157, MB_OK
Label37:
Label39:
MOV a@Variable,10
CALL salida3
MOV EAX,a@Variable
MOV EDX,salida3@Funcion
MUL EDX
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,-30
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@158, addr cadena@@158, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@159, addr cadena@@159, MB_OK
Label49:
MOV b@Variable,-3
CALL salida10
MOV EAX,salida10@Funcion
MOV EDX,b@Variable
MUL EDX
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,-30
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@160, addr cadena@@160, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@161, addr cadena@@161, MB_OK
Label57:
CALL salida3
MOV EAX,10
MOV EDX,salida3@Funcion
MUL EDX
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,-30
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@162, addr cadena@@162, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@163, addr cadena@@163, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion
MOV EDX,-3
MUL EDX
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,-30
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@164, addr cadena@@164, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@165, addr cadena@@165, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion
MOV EDX,salida3@Funcion
MUL EDX
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,-30
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@166, addr cadena@@166, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@167, addr cadena@@167, MB_OK
Label79:
MOV a@Variable,10
MOV c@Variable,-30
CALL salida3
MOV EAX,a@Variable
MOV EDX,salida3@Funcion
MUL EDX
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@168, addr cadena@@168, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@169, addr cadena@@169, MB_OK
Label88:
MOV EAX,10
MOV EDX,-3
MUL EDX
MOV var@@aux90,EAX
CALL salida30
MOV EAX,var@@aux90
CMP EAX,salida30@Funcion
JNE Label96
invoke MessageBox, NULL, addr cadena@@170, addr cadena@@170, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@171, addr cadena@@171, MB_OK
Label97:
invoke ExitProcess, 0
end start