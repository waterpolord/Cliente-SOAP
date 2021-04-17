#!/usr/bin/env python

# importamos la libreria zeep
from zeep import Client


url = "http://localhost:7000/ws/EstudianteWebServices?wsdl"
# definimos nuestro cliente
client = Client(url)


# Aqui se ejecutan las mismas funciones que en el service de Java
def findAll():
    return client.service.findAll()


def create(student):
    return client.service.create(student)


def findByMatricula(matricula):
    return client.service.findByMatricula(matricula)


def update(student):
    return client.service.update(student)


def deleteByMatricula(matricula):
    return client.service.deleteByMatricula(matricula)


def opciones():
    return "Elija la opcion:\n" \
           "1. Listar todos los estudiantes\n" \
           "2. Crear nuevo estudiante\n" \
           "3. Buscar por matricula\n" \
           "4. Actualizar estudiante\n" \
           "5. borrar estudiante\n" \
           "6. Salir\t"


opcion = 1

while (opcion >= 1 and opcion <= 6):

    print(opciones())
    opcion = int(input())
    if opcion == 1:
        print(findAll())
    elif opcion == 2:
        matricula = int(input("Ingrese Matricula: "))
        nombre = input("Ingrese Nombre: ")
        carrera = input("Ingrese Carrera: ")
        student = {'matricula': matricula,
        'nombre': nombre,
        'carrera': carrera}
        print(create(student))
    elif opcion == 3:
        mat = input("Ingrese matricula: ")
        print(findByMatricula(mat))
    elif opcion == 4:
        matricula = int(input("Ingrese Matricula: "))
        nombre = input("Ingrese Nombre nuevo: ")
        carrera = input("Ingrese Carrera nueva: ")
        student = {'matricula': matricula,
                   'nombre': nombre,
                   'carrera': carrera}
        print(update(student))
    elif opcion == 5:
        mat = int(input("Ingrese Matricula: "))
        if deleteByMatricula(mat):
            print("El estudiante se elimino con exito")
        else:
            print("no se pudo eliminar")
    elif opcion == 6:
        opcion = 7
        continue
    else:
        opcion = 1
        continue
