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
cadena@@230 DB "Multiplicación var = cte anda Long", 0
cadena@@231 DB "Multiplicación var = cte no anda Long", 0
cadena@@232 DB "Multiplicación var*var = cte anda Long", 0
cadena@@233 DB "Multiplicación var*var = cte no anda Long", 0
cadena@@234 DB "Multiplicación var*cte = cte anda Long", 0
cadena@@235 DB "Multiplicación var*cte = cte no anda Long", 0
cadena@@236 DB "Multiplicación cte*var = cte anda Long", 0
cadena@@237 DB "Multiplicación cte*var = cte no anda Long", 0
cadena@@238 DB "Multiplicación cte*cte = cte anda Long", 0
cadena@@239 DB "Multiplicación cte*cte = cte no anda Long", 0
cadena@@240 DB "Multiplicación var*fn = cte anda Long", 0
cadena@@241 DB "Multiplicación var*fn = cte no anda Long", 0
cadena@@242 DB "Multiplicación fn*var = cte anda Long", 0
cadena@@243 DB "Multiplicación fn*var = cte no anda Long", 0
cadena@@244 DB "Multiplicación cte*fn = cte anda Long", 0
cadena@@245 DB "Multiplicación cte*fn = cte no anda Long", 0
cadena@@246 DB "Multiplicación fn*cte = cte anda Long", 0
cadena@@247 DB "Multiplicación fn*cte = cte no anda Long", 0
cadena@@248 DB "Multiplicación fn*fn = cte anda Long", 0
cadena@@249 DB "Multiplicación fn*fn = cte no anda Long", 0
cadena@@250 DB "Multiplicación var*fn = var anda Long", 0
cadena@@251 DB "Multiplicación var*fn = var no anda Long", 0
cadena@@252 DB "Multiplicación cte*cte = fn anda Long", 0
cadena@@253 DB "Multiplicación cte*cte = fn no anda Long", 0
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
invoke MessageBox, NULL, addr cadena@@230, addr cadena@@230, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@231, addr cadena@@231, MB_OK
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
invoke MessageBox, NULL, addr cadena@@232, addr cadena@@232, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@233, addr cadena@@233, MB_OK
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
invoke MessageBox, NULL, addr cadena@@234, addr cadena@@234, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@235, addr cadena@@235, MB_OK
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
invoke MessageBox, NULL, addr cadena@@236, addr cadena@@236, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@237, addr cadena@@237, MB_OK
Label31:
MOV EAX,10
MOV EDX,3
MUL EDX
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,30
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@238, addr cadena@@238, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@239, addr cadena@@239, MB_OK
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
invoke MessageBox, NULL, addr cadena@@240, addr cadena@@240, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@241, addr cadena@@241, MB_OK
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
invoke MessageBox, NULL, addr cadena@@242, addr cadena@@242, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@243, addr cadena@@243, MB_OK
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
invoke MessageBox, NULL, addr cadena@@244, addr cadena@@244, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@245, addr cadena@@245, MB_OK
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
invoke MessageBox, NULL, addr cadena@@246, addr cadena@@246, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@247, addr cadena@@247, MB_OK
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
invoke MessageBox, NULL, addr cadena@@248, addr cadena@@248, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@249, addr cadena@@249, MB_OK
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
invoke MessageBox, NULL, addr cadena@@250, addr cadena@@250, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@251, addr cadena@@251, MB_OK
Label88:
MOV EAX,10
MOV EDX,3
MUL EDX
MOV var@@aux90,EAX
CALL salida30
MOV EAX,var@@aux90
CMP EAX,salida30@Funcion@main
JNE Label96
invoke MessageBox, NULL, addr cadena@@252, addr cadena@@252, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@253, addr cadena@@253, MB_OK
Label97:
invoke ExitProcess, 0
end start