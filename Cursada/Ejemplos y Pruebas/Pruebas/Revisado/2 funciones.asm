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
var@@aux14 DQ ?
var@@aux17 DQ ?
ab1@Variable DD ?
var@@aux1 DD ?
var@@aux4 DD ?
salida5@Funcion DD ?
aux@Variable DD ?
ac1@Variable DQ ?
aux3@Variable DQ ?
salida5d@Funcion DQ ?
aux2@Variable DQ ?
var@@aux12 DQ ?
son_iguales_y_anda_mal DB "son iguales y anda mal", 0
son_distintos_y_anda_bien DB "son distintos y anda bien", 0
anda_bien_la_funcion DB "anda bien la funcion", 0
la_cagamos DB "la cagamos", 0
const@@5_5 DQ 5.5
const@@6_0 DQ 6.0
const@@265_65 DQ 265.65
const@@48_3 DQ 48.3
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
FNINIT
CALL salida5
CALL salida5
MOV EAX,salida5@Funcion
ADD EAX,salida5@Funcion
MOV var@@aux4,EAX
MOV EAX,var@@aux4
MOV aux@Variable,EAX
MOV EDX,10
CMP aux@Variable,EDX
JE Label10
invoke MessageBox, NULL, addr son_iguales_y_anda_mal, addr son_iguales_y_anda_mal, MB_OK
JMP Label11
Label10:
invoke MessageBox, NULL, addr son_distintos_y_anda_bien, addr son_distintos_y_anda_bien, MB_OK
Label11:
CALL salida5d
FLD const@@6_0
FLD salida5d@Funcion
FADD
JO _overflow
FSTP var@@aux14
FLD var@@aux14
FSTP aux2@Variable
CALL salida5d
FLD const@@265_65
FLD salida5d@Funcion
FTST
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF

JE _division_cero
FDIV
FSTP var@@aux17
FLD var@@aux17
FSTP aux3@Variable
FLD aux3@Variable
FLD const@@48_3
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label23
invoke MessageBox, NULL, addr anda_bien_la_funcion, addr anda_bien_la_funcion, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr la_cagamos, addr la_cagamos, MB_OK
Label24:
invoke ExitProcess, 0
FNINIT
end start