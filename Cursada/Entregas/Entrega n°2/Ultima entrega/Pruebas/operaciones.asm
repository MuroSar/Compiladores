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
var@@aux16 DQ ?
var@@aux9 DQ ?
a@Variable DQ ?
z@Variable DQ ?
var@@aux2 DQ ?
var@@aux25 DQ ?
b@Variable DQ ?
Resta_bien DB "Resta bien", 0
Resta_mal DB "Resta mal", 0
Suma_bien DB "Suma bien", 0
Suma_mal DB "Suma mal", 0
Multiplica_bien DB "Multiplica bien", 0
Multiplica_mal DB "Multiplica mal", 0
divide_bien DB "divide bien", 0
divide_mal DB "divide mal", 0
const@@987_2 DQ 987.2
const@@123_5 DQ 123.5
const@@863_7 DQ 863.7
aux_mem_2bytes DW ?
const@@1110_7 DQ 1110.7
const@@121919_2 DQ 121919.2
const@@15_5 DQ 15.5
const@@2_5 DQ 2.5
const@@6_2 DQ 6.2
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
FNINIT
FLD const@@987_2
FSTP a@Variable
FLD const@@123_5
FSTP b@Variable
FLD a@Variable
FLD b@Variable
FFREE ST(0)
FFREE ST(1)
FWAIT
FSUB
FSTP var@@aux2
FLD var@@aux2
FSTP z@Variable
FLD z@Variable
FLD const@@863_7
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label8
invoke MessageBox, NULL, addr Resta_bien, addr Resta_bien, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr Resta_mal, addr Resta_mal, MB_OK
Label9:
FLD a@Variable
FLD b@Variable
FFREE ST(0)
FFREE ST(1)
FWAIT
FADD
JO _overflow
FSTP var@@aux9
FLD var@@aux9
FSTP z@Variable
FLD z@Variable
FLD const@@1110_7
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label15
invoke MessageBox, NULL, addr Suma_bien, addr Suma_bien, MB_OK
JMP Label16
Label15:
invoke MessageBox, NULL, addr Suma_mal, addr Suma_mal, MB_OK
Label16:
FLD a@Variable
FLD b@Variable
FFREE ST(0)
FFREE ST(1)
FWAIT
FMUL
FSTP var@@aux16
FLD var@@aux16
FSTP z@Variable
FLD z@Variable
FLD const@@121919_2
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label22
invoke MessageBox, NULL, addr Multiplica_bien, addr Multiplica_bien, MB_OK
JMP Label23
Label22:
invoke MessageBox, NULL, addr Multiplica_mal, addr Multiplica_mal, MB_OK
Label23:
FLD const@@15_5
FSTP a@Variable
FLD const@@2_5
FSTP b@Variable
FLD a@Variable
FLD b@Variable
FTST
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JE _division_cero
FDIV
FSTP var@@aux25
FLD var@@aux25
FSTP z@Variable
FLD z@Variable
FLD const@@6_2
FCOM
FSTSW AX
FFREE ST(0)
FFREE ST(1)
FWAIT
SAHF
JNE Label31
invoke MessageBox, NULL, addr divide_bien, addr divide_bien, MB_OK
JMP Label32
Label31:
invoke MessageBox, NULL, addr divide_mal, addr divide_mal, MB_OK
Label32:
invoke ExitProcess, 0
FNINIT
end start