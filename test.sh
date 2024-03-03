#!/bin/bash

# This script now accepts two parameters:
# 1. Source IP address
# 2. Destination IP address
# Usage: ./capture_packets.sh <source_ip> <destination_ip>

# Check if two arguments are passed
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <source_ip> <destination_ip>"
    exit 1
fi

# Assign the first argument to SRC_IP and the second to DST_IP
SRC_IP="$1"
DST_IP="$2"

# Define the interface and output file
INTERFACE="mirror8-output"
OUTPUT_FILE="captured_packets.pcap"

# Construct the filter string using the passed parameters
FILTER="src host $SRC_IP and dst host $DST_IP"

# Run tshark with the specified parameters
tshark -i $INTERFACE -f "$FILTER" 

