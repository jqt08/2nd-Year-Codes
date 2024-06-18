section .data
    x db 2, 3, 4   ; Define the array x with values 2, 3, and 4
    result db 0      ; Initialize a variable for storing the result

section .text
global _start

_start:
    ; Initialize registers
    mov eax, 0       ; Clear eax to store the sum
    mov ecx, 3       ; Set ecx to the number of elements in the array
    mov edi, x       ; Set edi to point to the beginning of the array

calculate_sum:
    ; Load the current element from the array into ebx
    movzx ebx, byte [edi]

    ; Add the current element to the sum in eax
    add eax, ebx

    ; Move to the next element in the array
    inc edi

    ; Decrement the loop counter
    loop calculate_sum

    ; Store the result in the "result" variable
    mov [result], al

    ; Convert the result to a printable character
    add byte [result], '0'

    ; Display the result
    mov eax, 4       ; sys_write
    mov ebx, 1       ; File descriptor (stdout)
    lea ecx, [result] ; Load the address of the result
    mov edx, 1       ; Length of the message
    int 0x80         ; Call the kernel

    ; Exit the program
    mov eax, 1       ; sys_exit
    xor ebx, ebx     ; Exit status (0)
    int 0x80
