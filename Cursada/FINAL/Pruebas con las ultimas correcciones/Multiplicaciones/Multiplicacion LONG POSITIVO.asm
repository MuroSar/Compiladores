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
var@@aux58 DD ?
var@@aux18 DD ?
a@Variable@main DD ?
salida3@Funcion@main DD ?
ab1@Variable@main@salida30 DD ?
var@@aux2 DD ?
var@@aux65 DD ?
c@Variable@main DD ?
var@@aux90 DD ?
var@@aux51 DD ?
var@@aux11 DD ?
salida10@Funcion@main DD ?
var@@aux38 DD ?
ab1@Variable@main@salida3 DD ?
ab1@Variable@main@salida10 DD ?
var@@aux82 DD ?
var@@aux40 DD ?
var@@aux43 DD ?
var@@aux89 DD ?
var@@aux25 DD ?
b@Variable@main DD ?
salida30@Funcion@main DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@256 DB "Multiplicación var = cte anda Long", 0
cadena@@257 DB "Multiplicación var = cte no anda Long", 0
cadena@@258 DB "Multiplicación var*var = cte anda Long", 0
cadena@@259 DB "Multiplicación var*var = cte no anda Long", 0
cadena@@260 DB "Multiplicación var*cte = cte anda Long", 0
cadena@@261 DB "Multiplicación var*cte = cte no anda Long", 0
cadena@@262 DB "Multiplicación cte*var = cte anda Long", 0
cadena@@263 DB "Multiplicación cte*var = cte no anda Long", 0
cadena@@264 DB "Multiplicación cte*cte = cte anda Long", 0
cadena@@265 DB "Multiplicación cte*cte = cte no anda Long", 0
cadena@@266 DB "Multiplicación var*fn = cte anda Long", 0
cadena@@267 DB "Multiplicación var*fn = cte no anda Long", 0
cadena@@268 DB "Multiplicación fn*var = cte anda Long", 0
cadena@@269 DB "Multiplicación fn*var = cte no anda Long", 0
cadena@@270 DB "Multiplicación cte*fn = cte anda Long", 0
cadena@@271 DB "Multiplicación cte*fn = cte no anda Long", 0
cadena@@272 DB "Multiplicación fn*cte = cte anda Long", 0
cadena@@273 DB "Multiplicación fn*cte = cte no anda Long", 0
cadena@@274 DB "Multiplicación fn*fn = cte anda Long", 0
cadena@@275 DB "Multiplicación fn*fn = cte no anda Long", 0
cadena@@276 DB "Multiplicación var*fn = var anda Long", 0
cadena@@277 DB "Multiplicación var*fn = var no anda Long", 0
cadena@@278 DB "Multiplicación cte*cte = fn anda Long", 0
cadena@@279 DB "Multiplicación cte*cte = fn no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
MOV ab1@Variable@main@salida3,3
MOV EAX,ab1@Variable@main@salida3
MOV salida3@Funcion@main,EAX
RET
salida3 endp
salida10 proc
MOV ab1@Variable@main@salida10,10
MOV EAX,ab1@Variable@main@salida10
MOV salida10@Funcion@main,EAX
RET
salida10 endp
salida30 proc
MOV ab1@Variable@main@salida30,30
MOV EAX,ab1@Variable@main@salida30
MOV salida30@Funcion@main,EAX
RET
salida30 endp
start:
MOV a@Variable@main,10
MOV b@Variable@main,3
MOV EAX,a@Variable@main
MOV EDX,b@Variable@main
MUL EDX
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,30
CMP c@Variable@main,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@256, addr cadena@@256, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@257, addr cadena@@257, MB_OK
Label9:
MOV a@Variable@main,10
MOV b@Variable@main,3
MOV EAX,a@Variable@main
MOV EDX,b@Variable@main
MUL EDX
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,30
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@258, addr cadena@@258, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@259, addr cadena@@259, MB_OK
Label17:
MOV a@Variable@main,10
MOV EAX,a@Variable@main
MOV EDX,3
MUL EDX
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,30
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@260, addr cadena@@260, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@261, addr cadena@@261, MB_OK
Label24:
MOV b@Variable@main,3
MOV EAX,10
MOV EDX,b@Variable@main
MUL EDX
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,30
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@262, addr cadena@@262, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@263, addr cadena@@263, MB_OK
Label31:
MOV EAX,10
MOV EDX,3
MUL EDX
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,30
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@264, addr cadena@@264, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@265, addr cadena@@265, MB_OK
Label37:
Label39:
MOV a@Variable@main,10
CALL salida3
MOV EAX,a@Variable@main
MOV EDX,salida3@Funcion@main
MUL EDX
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,30
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@266, addr cadena@@266, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@267, addr cadena@@267, MB_OK
Label49:
MOV b@Variable@main,3
CALL salida10
MOV EAX,salida10@Funcion@main
MOV EDX,b@Variable@main
MUL EDX
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,30
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@268, addr cadena@@268, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@269, addr cadena@@269, MB_OK
Label57:
CALL salida3
MOV EAX,10
MOV EDX,salida3@Funcion@main
MUL EDX
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,30
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@270, addr cadena@@270, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@271, addr cadena@@271, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion@main
MOV EDX,3
MUL EDX
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,30
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@272, addr cadena@@272, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@273, addr cadena@@273, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion@main
MOV EDX,salida3@Funcion@main
MUL EDX
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,30
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@274, addr cadena@@274, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@275, addr cadena@@275, MB_OK
Label79:
MOV a@Variable@main,10
MOV c@Variable@main,30
CALL salida3
MOV EAX,a@Variable@main
MOV EDX,salida3@Funcion@main
MUL EDX
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable@main
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@276, addr cadena@@276, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@277, addr cadena@@277, MB_OK
Label88:
MOV EAX,10
MOV EDX,3
MUL EDX
MOV var@@aux90,EAX
CALL salida30
MOV EAX,var@@aux90
CMP EAX,salida30@Funcion@main
JNE Label96
invoke MessageBox, NULL, addr cadena@@278, addr cadena@@278, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@279, addr cadena@@279, MB_OK
Label97:
invoke ExitProcess, 0
end start