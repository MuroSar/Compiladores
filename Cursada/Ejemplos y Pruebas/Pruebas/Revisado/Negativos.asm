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
var@@aux14 DD ?
var@@aux8 DD ?
var@@aux2 DD ?
pepe@Variable DD ?
var@@aux20 DD ?
var@@aux26 DD ?
var@Variable DD ?
c@Variable DD ?
mal DB "mal", 0
los_neg_andan_bien DB "los neg andan bien", 0
anda_mul_negativos DB "anda mul negativos", 0
no_anda_mul_negativos DB "no anda mul negativos", 0
anda_suma_negativos DB "anda suma negativos", 0
no_anda_suma_negativos DB "no anda suma negativos", 0
anda_resta_negativos DB "anda resta negativos", 0
no_anda_resta_negativos DB "no anda resta negativos", 0
anda_div_negativos DB "anda div negativos", 0
no_anda_div_negativos DB "no anda div negativos", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
start:
FNINIT
MOV var@Variable,-8
MOV pepe@Variable,7
MOV EAX,var@Variable
MOV EDX,pepe@Variable
MUL EDX
MOV var@@aux2,EAX
MOV EDX,56
CMP var@@aux2,EDX
JNE Label7
invoke MessageBox, NULL, addr mal, addr mal, MB_OK
JMP Label8
Label7:
invoke MessageBox, NULL, addr los_neg_andan_bien, addr los_neg_andan_bien, MB_OK
Label8:
MOV EAX,pepe@Variable
MOV EDX,var@Variable
MUL EDX
MOV var@@aux8,EAX
MOV EDX,-56
CMP var@@aux8,EDX
JNE Label13
invoke MessageBox, NULL, addr anda_mul_negativos, addr anda_mul_negativos, MB_OK
JMP Label14
Label13:
invoke MessageBox, NULL, addr no_anda_mul_negativos, addr no_anda_mul_negativos, MB_OK
Label14:
MOV EAX,var@Variable
ADD EAX,pepe@Variable
JO _overflow
MOV var@@aux14,EAX
MOV EDX,-1
CMP var@@aux14,EDX
JNE Label19
invoke MessageBox, NULL, addr anda_suma_negativos, addr anda_suma_negativos, MB_OK
JMP Label20
Label19:
invoke MessageBox, NULL, addr no_anda_suma_negativos, addr no_anda_suma_negativos, MB_OK
Label20:
MOV EAX,var@Variable
SUB EAX,pepe@Variable
MOV var@@aux20,EAX
MOV EDX,-15
CMP var@@aux20,EDX
JNE Label25
invoke MessageBox, NULL, addr anda_resta_negativos, addr anda_resta_negativos, MB_OK
JMP Label26
Label25:
invoke MessageBox, NULL, addr no_anda_resta_negativos, addr no_anda_resta_negativos, MB_OK
Label26:
MOV EAX,56
CDQ
MOV EBX,var@Variable
IDIV EBX
CMP var@Variable,0
JE _division_cero
MOV var@@aux26,EAX
MOV EAX,var@@aux26
MOV c@Variable,EAX
MOV EDX,-7
CMP c@Variable,EDX
JNE Label32
invoke MessageBox, NULL, addr anda_div_negativos, addr anda_div_negativos, MB_OK
JMP Label33
Label32:
invoke MessageBox, NULL, addr no_anda_div_negativos, addr no_anda_div_negativos, MB_OK
Label33:
invoke ExitProcess, 0
FNINIT
end start