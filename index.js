// main index.js

import { NativeModules } from 'react-native';

const { RNIpAndroid } = NativeModules;

export default class IP {
  /**
   * Runs getNeighbors command.
   *
   * Calls native module to run "ip neigh show" on device and returns parsed output or error.
   *
   * @returns {Promise} Promise containing parsed output of command or error.
   */
  static async getNeighbors() {
    const output = await RNIpAndroid.getNeighbors();

    const result = {};

    output.split('\n').forEach((line) => {
      const parts = line.split(' ');

      // ip address
      result[parts[0]] = {
        mac: parts[4],
        status: parts[5] // stale/reachable/failed
      };
    });

    return result;
  }
}
