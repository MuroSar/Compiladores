.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
var@@aux15 DD ?
var@@aux5 DD ?
var@@aux1 DD ?
var@@aux3 DD ?
suma@Variable DD ?
var@@aux21 DD ?
b@Variable DD ?
salida1@Funcion DQ ?
valor@Variable DQ ?
variable@Variable DD ?
c@Variable DD ?
var@@aux11 DD ?
var@@aux10 DD ?
ab1@Variable DQ ?
d@Variable DD ?
a@Variable DD ?
var@@aux31 DQ ?
var@@aux33 DQ ?
var@@aux35 DQ ?
MUL_ok DB "MUL ok", 0
entro_al_IF DB "entro al IF", 0
entro_al_ELSE DB "entro al ELSE", 0
Es_un_4 DB "Es un 4", 0
No_es_un_4 DB "No es un 4", 0
const@@6_8 DQ 6.8
const@@3_3 DQ 3.3
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida1 proc
FLD const@@6_8
FLD const@@3_3
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux31
FLD var@@aux31
FSTP ab1@Variable
FLD ab1@Variable
FSTP salida1@Funcion
RET
salida1 endp
start:
FNINIT
MOV d@Variable,2
MOV EAX,3
ADD EAX,5
JO _overflow
MOV var@@aux1,EAX
MOV EAX,var@@aux1
MOV a@Variable,EAX
MOV EAX,8
MOV EDX,3
MUL EDX
MOV var@@aux3,EAX
MOV EAX,var@@aux3
MOV c@Variable,EAX
MOV EAX,4
SUB EAX,2
MOV var@@aux5,EAX
MOV EAX,var@@aux5
MOV b@Variable,EAX
MOV EDX,24
CMP c@Variable,EDX
JNE Label10
invoke MessageBox, NULL, addr MUL_ok, addr MUL_ok, MB_OK
Label10:
MOV EAX,2
ADD EAX,5
JO _overflow
MOV var@@aux10,EAX
MOV EAX,8
SUB EAX,5
MOV var@@aux11,EAX
MOV EAX,var@@aux11
CMP var@@aux10,EAX
JGE Label19
MOV variable@Variable,6
MOV EAX,8
ADD EAX,2
JO _overflow
MOV var@@aux15,EAX
MOV EAX,var@@aux15
MOV suma@Variable,EAX
invoke MessageBox, NULL, addr entro_al_IF, addr entro_al_IF, MB_OK
JMP Label21
Label19:
MOV EAX,variable@Variable
MOV suma@Variable,EAX
invoke MessageBox, NULL, addr entro_al_ELSE, addr entro_al_ELSE, MB_OK
Label21:
MOV EAX,8
MOV EDX,3
MUL EDX
MOV var@@aux21,EAX
MOV EAX,var@@aux21
MOV c@Variable,EAX
invoke MessageBox, NULL, addr Es_un_4, addr Es_un_4, MB_OK
MOV variable@Variable,5
MOV EDX,4
CMP variable@Variable,EDX
JNE Label28
invoke MessageBox, NULL, addr Es_un_4, addr Es_un_4, MB_OK
Label28:
MOV EDX,5
CMP variable@Variable,EDX
JNE Label31
invoke MessageBox, NULL, addr No_es_un_4, addr No_es_un_4, MB_OK
Label31:
CALL salida1
FLD const@@3_3
FLD salida1@Funcion
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux35
FLD var@@aux35
FSTP valor@Variable
invoke ExitProcess, 0
FNINIT
end start