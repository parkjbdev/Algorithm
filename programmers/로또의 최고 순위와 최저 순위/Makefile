SRC=$(wildcard ./src/*.cpp)
BUILD_DIR := build
EXEC := build/main

CC = g++
CXXFLAGS	= -g -c -D_POSIX_C_SOURCE
CXXFLAGS += -std=c++17 -Wall 
CXXFLAGS += # -Wextra -Wno-unused-parameter -Wno-unused-function -Werror
LDFLAGS	=

.PHONY: clean
.SILENT: $(EXEC) $(BUILD_DIR)/%.o run

all: run 

run: $(EXEC)
	./$(EXEC)

$(EXEC): $(BUILD_DIR)/%.o
	$(CC) $(LDFLAGS) $^ -o $@

$(BUILD_DIR)/%.o: $(SRC) $(BUILD_DIR) 
	$(CC) $(CXXFLAGS) $< -o $@

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

.PHONY: clean
clean:
	rm -rv $(BUILD_DIR)
