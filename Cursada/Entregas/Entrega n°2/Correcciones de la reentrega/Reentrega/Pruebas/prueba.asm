.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
constCeroLong DD  0
__MIN DD  -2147483648
__MAX DD  2147483647
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
var@@aux15 DD ?
var@@aux5 DD ?
var@@aux1 DD ?
var@@aux3 DD ?
suma@Variable DD ?
var@@aux21 DD ?
b@Variable DD ?
salida1@Funcion DT ?
valor@Variable DT ?
variable@Variable DD ?
c@Variable DD ?
var@@aux11 DD ?
var@@aux10 DD ?
ab1@Variable DT ?
d@Variable DD ?
a@Variable DD ?
var@@aux30 DT ?
var@@aux32 DT ?
var@@aux34 DT ?
MULok DB "MUL ok", 0
entroalIF DB "entro al IF", 0
entroalELSE DB "entro al ELSE", 0
Esun4 DB "Es un 4", 0
Noesun4 DB "No es un 4", 0
const@@6_8 DT 6,8
const@@3_3 DT 3,3
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
FADD
JO _overflow
FSTP var@@aux30
FLD var@@aux30
FSTP ab1@Variable
FLD ab1@Variable
FSTP salida1@Funcion
RET
salida1 endp
start:
MOV d@Variable,2
MOV EAX,3
ADD EAX,5
JO _overflow
MOV var@@aux1,EAX
MOV EAX,var@@aux1
MOV a@Variable,EAX
MOV EAX,8
MOV EDX,3
IMUL EDX
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
invoke MessageBox, NULL, addr MULok, addr MULok, MB_OK
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
invoke MessageBox, NULL, addr entroalIF, addr entroalIF, MB_OK
JMP Label21
Label19:
MOV EAX,variable@Variable
MOV suma@Variable,EAX
invoke MessageBox, NULL, addr entroalELSE, addr entroalELSE, MB_OK
Label21:
MOV EAX,8
MOV EDX,3
IMUL EDX
MOV var@@aux21,EAX
MOV EAX,var@@aux21
MOV c@Variable,EAX
invoke MessageBox, NULL, addr Esun4, addr Esun4, MB_OK
MOV EDX,4
CMP variable@Variable,EDX
JNE Label27
invoke MessageBox, NULL, addr Esun4, addr Esun4, MB_OK
Label27:
MOV EDX,5
CMP variable@Variable,EDX
JNE Label30
invoke MessageBox, NULL, addr Noesun4, addr Noesun4, MB_OK
Label30:
CALL salida1
FLD const@@3_3
FLD salida1@Funcion
FADD
JO _overflow
FSTP var@@aux34
FLD var@@aux34
FSTP valor@Variable
Label36:
invoke ExitProcess, 0
end start