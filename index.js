// main index.js

import { NativeModules } from 'react-native';

const { IpAndroid } = NativeModules;

export default class IP {
  /**
   * Runs getNeighbors command.
   *
   * Calls native module to run "ip neigh show" on device and returns output or error.
   *
   * @returns {Promise} Promise containing string output of command or thrown error.
   */
  getNeighbors() {
    return IpAndroid.getNeighbors();
  }
}
