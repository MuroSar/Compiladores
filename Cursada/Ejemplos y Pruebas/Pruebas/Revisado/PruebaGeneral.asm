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
f@Variable DQ ?
var2@Variable DQ ?
var@@aux6 DD ?
var@@aux8 DQ ?
var@@aux2 DQ ?
var@@aux21 DQ ?
var@@aux23 DQ ?
b@Variable DD ?
h@Variable DD ?
var1@Variable DQ ?
c@Variable DD ?
var6@Variable DQ ?
g@Variable DQ ?
d@Variable DQ ?
a@Variable DD ?
var3@Variable DQ ?
var@@aux26 DQ ?
aa@Funcion DQ ?
e@Variable DQ ?
var@@aux29 DQ ?
var@@aux31 DQ ?
Invocacion_de_la_funcion DB "Invocacion de la funcion", 0
Termino DB "Termino", 0
IMPRIME DB "IMPRIME", 0
A DB "A", 0
B DB "B", 0
TERMINO DB "TERMINO", 0
const@@50_0 DQ 50.0
const@@1_0 DQ 1.0
const@@12_00 DQ 12.00
const@@4_00 DQ 4.00
const@@1_0e308 DQ 1.0e308
const@@1_7976931348623157e308 DQ 1.7976931348623157e308
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
aa proc
FLD const@@50_0
FLD var6@Variable
FFREE ST(0)
FFREE ST(1)
FWAIT
FMUL
FSTP var@@aux21
FLD var@@aux21
FSTP var3@Variable
FLD var6@Variable
FLD const@@1_0
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux23
FLD var@@aux23
FSTP var6@Variable
invoke MessageBox, NULL, addr Invocacion_de_la_funcion, addr Invocacion_de_la_funcion, MB_OK
FLD var3@Variable
FSTP aa@Funcion
RET
aa endp
start:
FNINIT
FLD const@@12_00
FSTP e@Variable
FLD const@@4_00
FSTP f@Variable
FLD e@Variable
FLD f@Variable
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux2
FLD var@@aux2
FSTP g@Variable
MOV a@Variable,2
MOV b@Variable,2147483647
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux6,EAX
MOV EAX,var@@aux6
MOV c@Variable,EAX
FLD const@@1_0e308
FLD const@@1_7976931348623157e308
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux8
FLD var@@aux8
FSTP d@Variable
invoke MessageBox, NULL, addr Termino, addr Termino, MB_OK
invoke MessageBox, NULL, addr IMPRIME, addr IMPRIME, MB_OK
MOV h@Variable,2
MOV EDX,2
CMP h@Variable,EDX
JNE Label16
invoke MessageBox, NULL, addr A, addr A, MB_OK
Label16:
MOV EDX,3
CMP h@Variable,EDX
JNE Label19
invoke MessageBox, NULL, addr B, addr B, MB_OK
Label19:
invoke MessageBox, NULL, addr TERMINO, addr TERMINO, MB_OK
FLD const@@1_0
FSTP var6@Variable
Label21:
CALL aa
CALL aa
FLD aa@Funcion
FLD aa@Funcion
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux29
CALL aa
FLD var@@aux29
FLD aa@Funcion
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux31
FLD var@@aux31
FSTP var3@Variable
invoke MessageBox, NULL, addr TERMINO, addr TERMINO, MB_OK
invoke ExitProcess, 0
FNINIT
end start