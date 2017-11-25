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
ab1@Variable DD ?
var@@aux1 DD ?
var@@aux4 DD ?
salida5@Funcion DD ?
aux@Variable DD ?
ac1@Variable DT ?
salida5d@Funcion DT ?
aux2@Variable DT ?
var@@aux10 DT ?
var@@aux12 DT ?
salidaaux DB "salida aux", 0
const@@11_1 DT 11,1
salidaaux2 DB "salida aux2", 0
lalala DB "lalala", 0
const@@5_5 DT 5,5
const@@6_0 DT 6,0
aux_mem_2bytes DW ?
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida5 proc
MOV ab1@Variable,5
MOV EAX,ab1@Variable
MOV salida5@Funcion,EAX
RET
salida5 endp
salida5d proc
FLD const@@5_5
FSTP ac1@Variable
FLD ac1@Variable
FSTP salida5d@Funcion
RET
salida5d endp
start:
CALL salida5
CALL salida5
MOV EAX,salida5@Funcion
ADD EAX,salida5@Funcion
MOV var@@aux4,EAX
MOV EAX,var@@aux4
MOV aux@Variable,EAX
MOV EDX,7
CMP aux@Variable,EDX
JLE Label9
invoke MessageBox, NULL, addr salidaaux, addr salidaaux, MB_OK
Label9:
CALL salida5d
FLD const@@6_0
FLD salida5d@Funcion
FADD
JO _overflow
FSTP var@@aux12
FLD var@@aux12
FSTP aux2@Variable
FLD aux2@Variable
FLD const@@11_1
FCOM
FSTSW aux_mem_2bytes
MOV AX, aux_mem_2bytes
SAHF
JLE Label18
invoke MessageBox, NULL, addr salidaaux2, addr salidaaux2, MB_OK
JMP Label19
Label18:
invoke MessageBox, NULL, addr lalala, addr lalala, MB_OK
Label19:
invoke ExitProcess, 0
end start